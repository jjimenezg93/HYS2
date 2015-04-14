function init(parent){
	
	listEvent("Pablo",function(events){
		
		insertRecommended(parent,events);
		insertDetailedEvent(parent,events[0]);
		
	},function(){},function(){});
	
	

	
}

function modificarDetailed(eventName,category,author,dateInit,image,description){
		
	var date = new Date(Number(dateInit)).toDateString();
	var documento= $("#detailedform");
	documento.find('input[name="eventname"]').val(eventName);
	documento.find('input[name="category"]').val(category);
	documento.find('input[name="author"]').val(author);
	documento.find('input[name="dateInit"]').val(date.toString());
	document.getElementById('description').innerHTML = description;
	document["eventImage"].src = image;

	
	
	
}
	

function insertDetailedEvent(parent,event) {

	parent.append('<!-- Features Section -->\
        <div class="row" > \
			<form id="' + "detailedform" + '">\
            <div class="col-lg-12">\
                <h2 class="page-header"><input type="text" name="eventname" value="'+event.nameEvent+'"/></h2>\
            </div>\
           <div class="col-md-6">\
                <ul>\
					<li><strong>Categoría: </strong><input type="text" name="category" value="'+event.category+'"/></li>\
					<li><strong>Creador: </strong><input type="text" name="author" value="'+event.author+'"/></li>\
                    <li><strong>Fecha de Inicio: </strong><input type="text" name="dateInit" value="'+new Date(Number(event.dateInit)).toDateString()+'"/></li>\
                </ul>\
                <p id="description">'+event.description+'</p>\
        		</form>\
            </div>\
            <div class="col-md-4 .col-md-offset-4">\
                <img name="eventImage" class="img-responsive" src="'+event.image+'" alt="">\
            </div>\
        </div>\
        <!-- /.row -->')
        
}

function insertRecommended(parent,events) {
	hola = events[0].category;
	parent.append('<div class="row">\
            <div class="col-lg-12">\
            <h2 class="page-header">Eventos recomendados</h2>\
        </div>\
        <div class="col-md-3 col-sm-6">\
                <img class="img-responsive img-portfolio img-hover" src="'+ events[0].image+'" alt="" onclick="modificarDetailed(\''+events[0].nameEvent+'\',\''+events[0].category+'\',\''+events[0].author+'\',\''+events[0].dateInit+'\',\''+events[0].image+'\',\''+events[0].description+'\')">\
        </div>\
        <div class="col-md-3 col-sm-6">\
                <img class="img-responsive img-portfolio img-hover" src="'+ events[1].image+'" alt="" onclick="modificarDetailed(\''+events[1].nameEvent+'\',\''+events[1].category+'\',\''+events[1].author+'\',\''+events[1].dateInit+'\',\''+events[1].image+'\',\''+events[1].description+'\');">\
        </div>\
        <div class="col-md-3 col-sm-6">\
                <img class="img-responsive img-portfolio img-hover" src="'+ events[2].image+'" alt="" onclick="modificarDetailed(\''+events[2].nameEvent+'\',\''+events[2].category+'\',\''+events[2].author+'\',\''+events[2].dateInit+'\',\''+events[2].image+'\',\''+events[2].description+'\');">\
            </a>\
        </div>\
        <div class="col-md-3 col-sm-6">\
                <img class="img-responsive img-portfolio img-hover" src="'+ events[3].image+'" alt="" onclick="modificarDetailed(\''+events[3].nameEvent+'\',\''+events[3].category+'\',\''+events[3].author+'\',\''+events[3].dateInit+'\',\''+events[3].image+'\',\''+events[3].description+'\');">\
        </div>\
    </div>')
}