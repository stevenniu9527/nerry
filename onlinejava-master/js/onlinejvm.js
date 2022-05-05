function commitCode() {
   var programInfo = document.getElementById("program").value;
   var jvmargsInfo = document.getElementById("jvmargs").value;
   document.getElementById("execResult").innerHTML = "";
   $('#commitBtn').attr('disabled',"true");

   document.getElementById("execResult").innerHTML = "";
   $.post("/execute", {program:programInfo, jvmargs:jvmargsInfo}, function(result) {
	   document.getElementById("execResult").innerHTML = result;
	   $('#commitBtn').removeAttr("disabled");
   });
}

