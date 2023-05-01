$('document').ready(function() {
	
	/*$('.table .btn-info').on('click',function(event){		
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(ficheProjet){
			$('#idEdit').val(ficheProjet.nomProjet);
		$('#descriptionEdit').val(ficheProjet.description);
		$('#clientEdit').val(ficheProjet.client);
		$('#etatEdit').val(ficheProjet.etatProjet);
		$('#coutEdit').val(ficheProjet.coutGlobal);
		$('#descSynEdit').val(ficheProjet.descSynthetique);
		$('#typeProjEdit').val(ficheProjet.typeProjet);
		$('#ressEdit').val(ficheProjet.ressources);		
		});		
		});			
		$('#editModal').modal();	*/
		
		
		
		
		
		
		
		$(document).on('click', '.btn-info', function(){

	var nomProjet= $('#nomProjet').text();
    var description= $('#description').text();

    var coutGlobal= $('#coutGlobal').text(); // get lastname

    var descSynthetique= $('#descSynthetique').text(); //get address
    $('#edit').modal('show');//load modal


    

	
	$('#idEdit').val(nomProjet);
		$('#descriptionEdit').val(description);
		//$('#clientEdit').val(client);
		//$('#etatEdit').val(ficheProjet.etatProjet);
		$('#coutEdit').val(coutGlobal);
		$('#descSynEdit').val(descSynthetique);
		//$('#typeProjEdit').val(ficheProjet.typeProjet);
		//$('#ressEdit').val(ficheProjet.ressources);		
		});		
		

   
		
			
	});
	
	
