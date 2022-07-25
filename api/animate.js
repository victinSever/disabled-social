// 延时动画
export const delay = function delay(){
	let $this = this
	var i=0;
	function delay_exec(){
		 if((i++)<5){
			  setTimeout(function (){
				$this.showIndex++
				return delay_exec();			
			  },1000);			        
		 }
	}
	delay_exec();
}