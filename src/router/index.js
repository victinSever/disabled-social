import nProgress from 'nprogress'
import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [{
    path: '/',
    redirect: '/login'
},
{
    path: '/login',
    meta: {
        title: '残疾人婚恋平台 - 登录',
        role: 0
    },
    component: () => import('@/views/login.vue')
},
{
    path: '/register',
    meta: {
        title: '残疾人婚恋平台 - 注册',
        role: 0
    },
    component: () => import('@/views/register.vue')
},
{
    path: '/404',
    meta: { title: '出错了，无此页面' },
    component: () => import('@/views/404.vue')
},

// 控制面板

{
    path: '/dashboard',
    meta: { title: '控制面板' },
    component: () => import('@/views/home/home.vue'),
    redirect: '/dashboard/workplace',
    children: [
        {
            path: 'workplace',
            meta: {
                title: '工作台 - MPDF',
                role: 1
            },
            component: () => import('@/views/dashboard/workplace.vue'),
        },
        {
            path: 'analysis',
            meta: {
                title: '分析页 - MPDF',
                role: 1
            },
            component: () => import('@/views/dashboard/analysis.vue'),
        },
        {
            path: 'monitor',
            meta: {
                title: '监控 - MPDF',
                role: 1
            },
            component: () => import('@/views/dashboard/monitor.vue'),
        }
    ]
},

// 系统

{
    path: '/system',
    meta: { title: '系统管理' },
    component: () => import('@/views/home/home.vue'),
    children: [
        {
            path: 'admin',
            meta: {
                title: '用户管理 - MPDF',
                role: 1
            },
            component: () => import('@/views/system/admin.vue'),
        },
        {
            path: 'menu',
            meta: {
                title: '菜单管理 - MPDF',
                role: 1
            },
            component: () => import('@/views/system/menu.vue'),
        }
    ]
},

// 业务

{
    path: '/business',
    component: () => import('@/views/home/home.vue'),
    children: [
        {
            path: 'adver',
            meta: {
                title: '广告管理 - MPDF',
                role: 1
            },
            component: () => import('@/views/business/adver.vue'),
        },
        {
            path: 'adSort',
            meta: {
                title: '广告位管理 - MPDF',
                role: 1
            },
            component: () => import('@/views/business/adSort.vue'),
        },
        {
            path: 'activity',
            meta: {
                title: '活动管理 - MPDF',
                role: 1
            },
            component: () => import('@/views/business/activity.vue'),
        },
        {
            path: 'celebration',
            meta: {
                title: '节日庆典 - MPDF',
                role: 1
            },
            component: () => import('@/views/business/celebration.vue'),
        },
        {
            path: 'vip',
            meta: {
                title: 'VIP充值 - MPDF',
                role: 1
            },
            component: () => import('@/views/business/vip.vue'),
        }
    ]
},

// 用户

{
    path: '/user',
    meta: { title: '日志管理' },
    component: () => import('@/views/home/home.vue'),
    children: [
        {
            path: 'commontUser',
            meta: {
                title: '用户管理 - MPDF',
                role: 1
            },
            component: () => import('@/views/user/commontUser.vue'),
        },
    ]
},

// 个人中心

{
    path: '/person',
    meta: { title: '个人中心' },
    component: () => import('@/views/home/home.vue'),
    children: [
        {
            path: 'personData',
            meta: {
                title: '个人资料 - MPDF',
                role: 0
            },
            component: () => import('@/views/person/personData.vue'),
        },
    ]
},

// 日志

{
    path: '/log',
    meta: { title: '日志管理' },
    component: () => import('@/views/home/home.vue'),
    children: [
        {
            path: 'loginLog',
            meta: {
                title: '登录日志 - MPDF',
                role: 1
            },
            component: () => import('@/views/log/loginLog.vue'),
        },
        {
            path: 'opeartionLog',
            meta: {
                title: '操作日志 - MPDF',
                role: 1
            },
            component: () => import('@/views/log/opeartionLog.vue'),
        }
    ]
},

// 审核

{
    path: '/audit',
    meta: { title: '审核模块' },
    component: () => import('@/views/home/home.vue'),
    redirect: '/audit/adver',
    children: [
        {
            path: 'activeAudit',
            meta: {
                title: '动态审核 - MPDF',
                role: 2
            },
            component: () => import('@/views/audit/activeAudit.vue'),
        },
        {
            path: 'infoAudit',
            meta: {
                title: '信息审核 - MPDF',
                role: 2
            },
            component: () => import('@/views/audit/infoAudit.vue'),
        },
        {
            path: 'vedioAudit',
            meta: {
                title: '视频审核 - MPDF',
                role: 2
            },
            component: () => import('@/views/audit/vedioAudit.vue'),
        }
    ]
},
]

const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
})

//为路由对象加上导航守卫
router.beforeEach((to, from, next) => {
    document.title = to.meta.title; //设置标题
    nProgress.start()

    if (to.path === '/login' || to.path === '/register') {
        next()
    } else {
        const token = sessionStorage.getItem('token');
        if (!token) {
            next('/login')
        } else {
            let role = JSON.parse(localStorage.getItem('userInfo')).role || ''
            if (role == to.meta.role || to.meta.role == 0) {
                next()
            } else {
                next('/404')
            }

        }
    }
    nProgress.done()

})

export default router