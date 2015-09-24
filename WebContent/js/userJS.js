/*fichier javascript user*/
var pb=function(input){
	var id=input.id+"";
	if(!input.changed && !(id.indexOf("questionsContainer") === 0 || id.indexOf("value-") === 0)){
		var pb=$("pb");
		if(!pb.value){
			pb.value=0;
		}
		pb.value=pb.value+1;
		var newValue=Math.round((pb.value/20.0)*100);
		if(newValue>100)
			newValue=100;
		$("pb").style.width=newValue+"%";
		span=new Forms.Elements("#pb span");span.html(newValue+"%");
		input.changed=true;
	}
};