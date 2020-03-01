 

 $(document).ready(function(){
	 var button=false;
	 $("#botonBurguerEnlace").click(function(){
		
		 
		 if(!button){
			 $('#slideMenu').show( "slow" );
			 button=true;
		 }else{
			 $('#slideMenu').hide( "slow" );
			 button=false;
		 }
		 
		 });
	 
	 });
 
 
 