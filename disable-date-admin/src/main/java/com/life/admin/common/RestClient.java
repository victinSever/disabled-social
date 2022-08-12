package com.life.admin.common;

import com.alibaba.fastjson.JSON;
import com.life.admin.pojo.Diary;
import com.life.admin.service.IDiaryService;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.*;


/**
 * es搜索的方法，有些是动态的，在service层可以传参调用即可
 */
@Controller
public class RestClient {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private IDiaryService diaryService;


    //删除索引库
    public void DeleteIndex() throws IOException {
        //1.创建Request对象
        DeleteIndexRequest request = new DeleteIndexRequest("disable-date");

        //2.发送请求
        client.indices().delete(request,RequestOptions.DEFAULT);
    }

    //判断索引库是否存在
    public void ExitsIndex(String index) throws IOException {
        //1.创建Request对象
        GetIndexRequest request = new GetIndexRequest(index);
        //2.发送请求
        boolean exists = client.indices().exists(request,RequestOptions.DEFAULT);
        System.out.println(exists ? "索引库已经存在！" : "索引库不存在！");
    }


//    //添加文档（单个）
//    public void AddDocument() throws IOException {
//
//        // 根据id查询酒店数据
//        Diary diary = diaryService.getById(1);
//
//        //转换为文档类型
////        Diary hotelDoc = new HotelDoc(hotel);
//
//        //1.准备Request对象
//        IndexRequest request = new IndexRequest("disable-date").id(diary.getId().toString());
//        //2.准备Json文档
//        request.source(JSON.toJSONString(diary),XContentType.JSON);
//        //3.发送请求
//        client.index(request,RequestOptions.DEFAULT);
//    }

    //根据id查询
    public String GetDocumentById(String index,String id) throws IOException {

        //1.准备好request
        GetRequest request = new GetRequest(index,id);
        //2.发送请求 ，得到响应
        GetResponse response = client.get(request,RequestOptions.DEFAULT);
        //3.解析响应结果
        String json = response.getSourceAsString();
        return json;
//        Diary diary= JSON.parseObject(json,Diary.class);
//        System.out.println("查询到的是diary ："+diary);
    }

    //修改文档
    public void UpdateDocument() throws IOException {
        //1.准备Request
        UpdateRequest request = new UpdateRequest("disable-date","1");

        //2.准备请求参数
        request.doc(
                "diaryTitle","后端相对更卷啊！",
                "diaryUserId","10"
        );
        //3.发送请求
        client.update(request,RequestOptions.DEFAULT);
    }

    //删除文档
    public void DeleteDocument(String index , String id) {
        try {
            //1.准备Request
            DeleteRequest request = new DeleteRequest(index,id);
            //2.备请求参数
            client.delete(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //match查询
    public SearchHit[] matchQuery(String index,String name,String text) throws IOException {
        //1.准备Request
        SearchRequest request = new SearchRequest(index);
        //2.准备DSL                          match查询  在name字段中，查询text的
        request.source().query(QueryBuilders.matchQuery(name,text));
        //3.发送请求
        SearchResponse response = client.search(request,RequestOptions.DEFAULT);

        //解析响应
        SearchHit[] hits = handleResponce(response);

        System.out.println(response);
        return hits;
    }

    //bool查询
    /**
     *
     * @param index  索引库名
     * @param name   字段名
     * @param text   匹配的内容
     * @return
     * @throws IOException
     */
    public SearchHit[] boolQuery_termQuery_getAllByOneText(String index, String name, String text) throws IOException {
        //1.准备Request
        SearchRequest request = new SearchRequest(index);

        //2.准备DSL
        //2.1准备BooleanQuery
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //2.2 添加term                term
        boolQuery.must(QueryBuilders.termQuery(name,text));
        //2.3添加 range
//        boolQuery.filter(QueryBuilders.rangeQuery("price").lte(250));

        request.source().query(boolQuery);
        //3.发送请求
        SearchResponse response = client.search(request,RequestOptions.DEFAULT);

        SearchHit[] hits = handleResponce(response);

//        System.out.println(response);
        return hits;
    }

    //解析响应
    public SearchHit[] handleResponce(SearchResponse response) {
        //4.解析响应
        SearchHits searchHits = response.getHits();
        //4.1获取总条数
        long total = searchHits.getTotalHits().value;
        System.out.println("共搜索到"+total+"条数据");
        //4.2文档数据
        SearchHit[] hits = searchHits.getHits();
        return hits;
    }

    //matchAll查询（matchAllQuery）
    /**
     *
     * @param index  索引库
     * @param sortName  排序依据的的字段
     * @param Page      //页
     * @param Size      //一页的量
     * @param sortOrder //升序 or 降序
     * @return
     * @throws IOException
     */
    //查询多条动态信息  根据动态的发表时间进行降序（也就是最近发表的越靠前）
    public SearchHit[] getAllByISSPG(String index, String sortName,String Page,String Size,String sortOrder,
                                     String esName,String id,Map<String,String> es) {

        try {
            Integer page = -1;
            Integer size = -1;
            int start = -1;  //标识从哪一个条开始查询
            if(Page != null) {   //如果有page不为空，也就是表示需要按照页数来查询
                page = Integer.parseInt(Page);
                size = Integer.parseInt(Size);
                start = (page - 1) * size;  //标识从哪一个条开始查询
            }

            //1.准备Request
            SearchRequest request = new SearchRequest(index);

            //2.准备DSL
            //2.1 query
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();  //BooleanQuery
            if(esName == null) {
                request.source().query(QueryBuilders.matchAllQuery());
            }else {
                //2.2 添加term                term
                boolQuery.must(QueryBuilders.termQuery(esName,id));
                //2.3添加 range
    //        boolQuery.filter(QueryBuilders.rangeQuery("price").lte(250));
            }

            filters_must(boolQuery,es);   //过滤查询

            request.source().query(boolQuery);

            //2.2 排序 sort
            if(sortOrder.equals("DESC"))
                request.source().sort(sortName, SortOrder.DESC);  //根据动态的发表时间进行降序（也就是最近发表的越靠前）
            else if(sortOrder.equals("ASC"))
                request.source().sort(sortName, SortOrder.ASC);  //根据动态的发表时间进行升序（也就是最早发表的越靠前）

            //2.3 分页 from 、size
            if(start != -1)   //代表需要按照页数来查询
                request.source().from(start).size(size);

            //3.发送请求
            SearchResponse response = client.search(request,RequestOptions.DEFAULT);

            SearchHit[] hits = handleResponce(response);

//        System.out.println(response);
            return hits;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param index         索引库名
     * @param sortName      排序的字段名
     * @param sortOrder     排序规则 （升序 降序）
     * @param page          获取第几页
     * @param size          一页的数量
     * @param esName        term查询的索引字段
     * @param es            esName的值
     * @return
     */
    public SearchHit[] getAllBy_Index_SortName_SortOrder_page_size_esName_esList(String index, String sortName,String sortOrder,Integer page,Integer size,
                                     Map<String,String> map,String esName,List<String> es,boolean flag) {

        try {
            int start = -1;  //标识从哪一个条开始查询
            if(page != null) {   //如果有page不为空，也就是表示需要按照页数来查询
                start = (page - 1) * size;  //标识从哪一个条开始查询
            }

            //1.准备Request
            SearchRequest request = new SearchRequest(index);

            //2.准备DSL
            //2.1 query
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();  //BooleanQuery
            request.source().query(QueryBuilders.matchAllQuery());   //搜索所有

            filters_must(boolQuery,map);   //过滤查询
            if(flag == true)
                filters_should_termQuery(boolQuery,esName,es);
            else if(flag == false)
                filters_mustNot_termQuery(boolQuery,esName,es);

            request.source().query(boolQuery);

            //2.2 排序 sort
            if(sortOrder.equals("DESC"))
                request.source().sort(sortName, SortOrder.DESC);  //根据动态的发表时间进行降序（也就是最近发表的越靠前）
            else if(sortOrder.equals("ASC"))
                request.source().sort(sortName, SortOrder.ASC);  //根据动态的发表时间进行升序（也就是最早发表的越靠前）

            //2.3 分页 from 、size
            if(start != -1)   //代表需要按照页数来查询
                request.source().from(start).size(size);

            //3.发送请求
            SearchResponse response = client.search(request,RequestOptions.DEFAULT);

            SearchHit[] hits = handleResponce(response);

//        System.out.println(response);
            return hits;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param index
     * @param sortName
     * @param sortOrder
     * @param Page
     * @param Size
     * @param esMust     必须匹配条件 &&
     * @param esShould   选择性条件  ||
     * @return
     */
    public SearchHit[] getAllByISSPSEE(String index, String sortName,String sortOrder,String Page,String Size,
                                      Map<String,String> esMust,Map<String,String[]> esShould) {

        try {
            Integer page = -1;
            Integer size = -1;
            int start = -1;  //标识从哪一个条开始查询
            if(Page != null) {   //如果有page不为空，也就是表示需要按照页数来查询
                page = Integer.parseInt(Page);
                size = Integer.parseInt(Size);
                start = (page - 1) * size;  //标识从哪一个条开始查询
            }

            //1.准备Request
            SearchRequest request = new SearchRequest(index);

            //2.准备DSL
            //2.1 query
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();  //BooleanQuery
            request.source().query(QueryBuilders.matchAllQuery());   //搜索所有

            filters_must(boolQuery,esMust);       //对 必须匹配的条件 进行过滤查询


            Set<String> keys = esShould.keySet();
            for(String key : keys){
                System.out.println(esShould+" ===> "+esShould.get(key));
            }
            filters_should(boolQuery,esShould);   //对 选择性匹配的条件 进行过滤查询
            request.source().query(boolQuery);

            //2.2 排序 sort
            if(sortOrder.equals("DESC"))
                request.source().sort(sortName, SortOrder.DESC);  //根据动态的发表时间进行降序（也就是最近发表的越靠前）
            else if(sortOrder.equals("ASC"))
                request.source().sort(sortName, SortOrder.ASC);  //根据动态的发表时间进行升序（也就是最早发表的越靠前）

            //2.3 分页 from 、size
            if(start != -1)   //代表需要按照页数来查询
                request.source().from(start).size(size);

            //3.发送请求
            SearchResponse response = client.search(request,RequestOptions.DEFAULT);
            SearchHit[] hits = handleResponce(response);

//        System.out.println(response);
            return hits;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void filters_must(BoolQueryBuilder boolQuery, Map<String, String> es) {
        /**
         * 条件过滤
         */
        Set<String> keys = es.keySet();  //遍历Map集合
        for(String Key : keys){
            String Value = es.get(Key);
            System.out.println(Key + "-->"+Value);
            Integer value = 0;
            if(Value.length() == 1 && Character.isDigit(Value.charAt(0)))   //如果是一个个位数的数字，代表是状态status
                value= Integer.parseInt(Value);
            //过滤条件
            if(es.get(Key) != null && !es.get(Key).equals("")){
                boolQuery.filter(QueryBuilders.termQuery(Key, es.get(Key)));
            }
        }
    }

    public void filters_should(BoolQueryBuilder boolQuery, Map<String, String[]> es) {
        /**
         * 条件过滤
         */
        Set<String> keys = es.keySet();  //遍历Map集合
        for(String key : keys){

            System.out.println(es+" ==p---------=> "+es.get(key));
            Integer value = 0;
            for(String Value : es.get(key)){
                if(Value.length() == 1 && Character.isDigit(Value.charAt(0)))
                {
                    value= Integer.parseInt(Value);
                }
                System.out.println(es+"="+value);

                //过滤条件
                if(Value != null && !Value.equals("")){
                    boolQuery.should(QueryBuilders.matchQuery(key, Value));
                }
                boolQuery.minimumShouldMatch(1);
            }

        }
    }

    /**
     * 通过id集合来查询
     *                  只要符合id值有一个匹配就获取出来
     * @param boolQuery
     * @param esName
     * @param es
     */
    public void filters_should_termQuery(BoolQueryBuilder boolQuery,String esName ,List<String> es) {
        System.out.println(" ==p---------=> "+esName);
        Integer value = 0;
        for(String userId : es){
            System.out.print(userId+" ");
            //过滤条件
            boolQuery.should(QueryBuilders.termQuery(esName,userId));
        }
        boolQuery.minimumShouldMatch(1);
        System.out.println();
    }

    /**
     * 必须不匹配过滤查询
     * @param boolQuery
     * @param esName
     * @param es
     */
    public void filters_mustNot_termQuery(BoolQueryBuilder boolQuery,String esName ,List<String> es) {
        System.out.println(" ==p---------=> "+esName);
        Integer value = 0;
        for(String userId : es){
            System.out.print(userId+" ");
            //过滤条件
            boolQuery.mustNot(QueryBuilders.termQuery(esName,userId));
        }
        System.out.println();
    }

    public void filters_moreLike_should(BoolQueryBuilder boolQuery, Set<String> hobbiesSet,String name) {
        /**
         * 条件过滤
         */
        for(String hobby : hobbiesSet){

            //过滤条件
            if(hobby != null && !hobby.equals("")){
                System.out.println(name+"-->"+hobby);
                boolQuery.should(QueryBuilders.matchQuery(name, hobby));
            }
            boolQuery.minimumShouldMatch(1);

        }
    }


//    ——————————————————————————————————————多条件聚合start————————————————————————————————————————————————————————————————————————
    //多条件聚合

    /**
     *
     * @param index      索引库
     * @param names      条件集合
     * @param params
     * @param scoreName  评分字段名
     * @return
     */
    public Map<String, List<String>> filters(String index,List<String[]> names,Map<String,String> params,String scoreName)  {

        try {
            //1. 准备Request
            SearchRequest request = new SearchRequest(index);
            //2. 准备DSL

            //2.1 query
            buildbasicQuery(params, request,scoreName);                                                //过滤查询

            //2.2 设置size
            request.source().size(0);
            //2.3 聚合
            buildAggretgaion(request,names);                                                 //条件聚合

            //3. 发出请求
            SearchResponse response = client.search(request,RequestOptions.DEFAULT);

            //4. 解析结果
            Map<String,List<String>> result = new HashMap<String, List<String>>();
            Aggregations aggregations = response.getAggregations();                         //解析结果

            for(int i = 0;i<names.size();i++){
                //根据 聚合名称 获取品牌的结果
                List<String> list = getAddByName(aggregations,names.get(i)[0]);
                //放入map
                result.put(names.get(i)[1],list);
            }

//            //4.1 根据品牌（聚合名称）名称获取品牌的结果
//            List<String> brandList = getAddByName(aggregations,"brandAgg");
//            result.put("品牌",brandList); //放入map
//            //4.2 根据城市（聚合名称）名称获取品牌的结果
//            List<String> cityList = getAddByName(aggregations,"cityList");
//            result.put("城市",cityList); //放入map
//            //4.3 根据星级（聚合名称）名称获取品牌的结果
//            List<String> starList = getAddByName(aggregations,"starList");
//            result.put("星级",starList); //放入map

            return result;

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    //(抽取出来的) 根据名称获取的结果
    public List<String> getAddByName(Aggregations aggregations, String aggName) {
        //4.1 根据聚合名称获取聚合结果
        Terms brandTerms = aggregations.get(aggName);
        //4.2 获取buckets
        List<? extends Terms.Bucket> buckets = brandTerms.getBuckets();
        //4.3 遍历
        List<String> brandList = new ArrayList<String>();
        for(Terms.Bucket bucket : buckets){
            //4.4 获取key
            String key = bucket.getKeyAsString();
            brandList.add(key);

            System.out.println(key);
        }
        return brandList;
    }

    //抽取出来的 聚合
    public void buildAggretgaion(SearchRequest request,List<String[]> names) {
        for(int i = 0;i<names.size();i++){
            String termsName = names.get(i)[0];
            String fieldName = names.get(i)[1];
            request.source().aggregation(AggregationBuilders
                    .terms(termsName)
                    .field(fieldName)
                    .size(10)
            );
        }
    }
//   ———————————————————————————————————————多条件聚合end———————————————————————————————————————————————————————————————————————


    /**
     * 距离范围内查询（圆心）
     * @param request
     * @param filed
     * @param location
     * @param distance
     * @return
     */
    public SearchRequest sortDistance( SearchRequest request, String filed,String location,String distance){

        if(location != null && !location.equals("")){
            request.source().sort(SortBuilders
                    .geoDistanceSort("location",new GeoPoint(location))   //圆心点
                    .order(SortOrder.ASC)           //升序
                    .unit(DistanceUnit.KILOMETERS)  //单位km
            );
        }
        return request;
    }

    /**
     * 距离范围内查询（圆心）
     * @param filed     字段名
     * @param location  地点的经度度和纬度，作为圆心点
     * @param distance  范围距离
     * @return
     */
//    public SearchSourceBuilder sortDistance(SearchSourceBuilder builder, String filed,String location,String distance){
//
//        if(location != null && !location.equals("")){
//            request.source().sort(SortBuilders
//                    .geoDistanceSort("location",new GeoPoint(location))   //圆心点
//                    .order(SortOrder.ASC)           //升序
//                    .unit(DistanceUnit.KILOMETERS)  //单位km
//            );
//        }
//        QueryBuilder qb = QueryBuilders.geoDistanceQuery("location")
//                .point(new GeoPoint(location))
//                .distance("10",DistanceUnit.KILOMETERS);
//        builder.query(qb);
//        GeoDistanceSortBuilder sortBuilder = SortBuilders.geoDistanceSort("location",new GeoPoint(location))
//                .order(SortOrder.ASC)
//                .unit(DistanceUnit.KILOMETERS);
//        builder.sort(sortBuilder);
//        return builder;
//    }

    //(抽取出来的)构建BooleanQuery
    /**
     *
     * @param params   前台传来的 过滤条件 集合
     * @param request
     * @param scoreName 打分的依据字段
     */
    private void buildbasicQuery(Map<String,String> params, SearchRequest request,String scoreName) {
//        构建BooleanQuery
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //关键字搜索
        String key = params.get("key");
        if(key == null || "".equals(key)){
            boolQuery.must(QueryBuilders.matchAllQuery());
        }else {
            boolQuery.must(QueryBuilders.matchQuery("all",key));
        }

        /**
         * 条件过滤
         */
        Set<String> keys = params.keySet();  //遍历Map集合
        for(String Key : keys){
            String value = params.get(Key);
            System.out.println(key+"="+value);

            //过滤条件
            if(params.get(Key) != null && !params.get(Key).equals("")){
                boolQuery.filter(QueryBuilders.termQuery(Key, params.get(Key)));
            }
        }


        //进行算分控制
        if(scoreName != null)
            query_Score(request, boolQuery,scoreName);
        
    }


    //抽取出来的  算分控制
    public void query_Score(SearchRequest request, BoolQueryBuilder boolQuery,String name){
                //2. 算分控制
        FunctionScoreQueryBuilder functionScoreQuery =
                QueryBuilders.functionScoreQuery(
                        //原始查询，相关性算法的查询
                        boolQuery,
                        // function score的数组
                        new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                                //其中的一个function score元素
                                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                                        //过滤条件
//                                        QueryBuilders.termQuery("isAD",true),
                                        QueryBuilders.termQuery(name,true),
                                        //算分函数
                                        ScoreFunctionBuilders.weightFactorFunction(10)
                                )
                        });

        request.source().query(functionScoreQuery);
    }


    /**
     * 自动补全查询
     * @param index         索引库
     * @param prefixName    关键字前端
     * @param fieldName     补全依据的字段
     * @throws IOException
     * @return
     */
    public List<CompletionSuggestion.Entry.Option> searchSuggestion(String index, String prefixName, String fieldName) throws IOException {
        //1.准备Request
        SearchRequest request = new SearchRequest(index);
        //2.准备DSL
        request.source().suggest(new SuggestBuilder().addSuggestion(
                "suggestions",   /*补全查询名称*/
                SuggestBuilders.completionSuggestion(fieldName)  //fieldname
                        .prefix(prefixName)
                        .skipDuplicates(true)
                        .size(10)
        ));
        //3.发起请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        //4.解析结果
        Suggest suggest = response.getSuggest();
        //4.1 根据补全查询名称，获取更全的结果
        CompletionSuggestion suggestions = suggest.getSuggestion("suggestions");
        //4,2 获取options
        List<CompletionSuggestion.Entry.Option> options = suggestions.getOptions();
        //4.3 遍历
        List<String> list = new ArrayList<String>();
        for( CompletionSuggestion.Entry.Option option : options){
            String text = option.getText().toString();
            System.out.println(text);
            list.add(text);
        }

//        System.out.println(response);
        return options;
    }

    //关键字子搜索 得到 匹对的用户详细信息的（用详细信息的标签，爱好来进行匹对）
    public SearchHit[] getDetailByMatchKeyWords(String index, Set<String> keywords, List<String> esName,Integer max, Integer start,Integer size) {
        try {
            //1.准备Request
            SearchRequest request = new SearchRequest(index);

            //2.准备DSL
            //2.1 query
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();  //BooleanQuery
            request.source().query(QueryBuilders.matchAllQuery());   //搜索所有

            for(String esname : esName) {
                System.out.println("搜索的字段名-->"+esname);
                for (String keyword : keywords) {
                    System.out.println(keyword);
                    //过滤条件
                    if (keyword != null && !keyword.equals("")) {
                        boolQuery.should(QueryBuilders.matchQuery(esname, keyword));
                    }
                }
                boolQuery.minimumShouldMatch(1);
            }
//        filters_should(boolQuery,esShould);   //对 选择性匹配的条件 进行过滤查询
//            BoolQueryBuilder boolQueryShould = QueryBuilders.boolQuery();  //BooleanQuery
//            boolQuery.must(boolQueryShould);
            request.source().query(boolQuery);
            request.source().size(max);

//            if(start != -1)   //代表需要按照页数来查询
//                request.source().from(start).size(size);


            //3.发送请求
            SearchResponse response = client.search(request,RequestOptions.DEFAULT);

            SearchHit[] hits = handleResponce(response);

//        System.out.println(response);
            return hits;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //根据 年龄范围、距离范围、性别 筛选出所有的用户基本信息
    public SearchHit[] getAllByAgeDistanceSex(String index,Integer age1,Integer age2, Integer distance, Integer sex) {

        try {
            SearchRequest request = new SearchRequest(index);
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();  //BooleanQuery

            //2.3添加 range
            boolQuery.filter(QueryBuilders.rangeQuery("age").gte(age1).lte(age2)); //  年龄过滤（大于等于 18，小于等于age）
            boolQuery.filter(QueryBuilders.termQuery("sex",sex));               //性别过滤
            request.source().query(boolQuery);
            //发送请求
            SearchResponse response = client.search(request,RequestOptions.DEFAULT);
            SearchHit[] hits = handleResponce(response);
            return hits;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //获取自己的所有动态列表
    public SearchHit[] getMyDiary(String index, String esName, String userId, Integer page, Integer size,String sortName,String sort) {

        try {
            SearchRequest request = new SearchRequest(index);
            BoolQueryBuilder booleanQuery = QueryBuilders.boolQuery();
            booleanQuery.must(QueryBuilders.termQuery(esName,userId));
            request.source().query(booleanQuery);
            //2.2 排序 sort
            if(sort.equals("DESC"))
                request.source().sort(sortName, SortOrder.DESC);  //根据动态的发表时间进行降序（也就是最近发表的越靠前）
            else if(sort.equals("ASC"))
                request.source().sort(sortName, SortOrder.ASC);  //根据动态的发表时间进行升序（也就是最早发表的越靠前）
            Integer start = (page - 1)*size;
            //2.3 分页 from 、size
            request.source().from(start).size(size);
            //发送请求
            SearchResponse response = client.search(request,RequestOptions.DEFAULT);
            SearchHit[] hits = handleResponce(response);
            return hits;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
