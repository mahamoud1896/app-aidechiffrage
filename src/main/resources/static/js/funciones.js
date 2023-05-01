
function supprimer(id) {
	console.log(id);
	swal({
		  title: "Are you sure?",
		  text: "Once deleted you can't restablish'!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((OK) => {
		  if (OK) {
			  $.ajax({
				 url:"/deleteFP/"+id,
				 success: function(res) {
					console.log(res);
				},			
			  });
		    swal("Poof! Rssource deleted!", {
		      icon: "success",
		    }).then((ok)=>{
		    	if(ok){
		    		location.href="/listProj";
		    	}
		    });
		  } else {
			swal("Your imaginary file is safe!");
		}
		});
}