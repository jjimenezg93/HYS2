function init(parent){
	
	listEvent("logon",function(events){
		
		insertRecommended(parent,events);
		insertDetailedEvent(parent,events[0]);
		
	},function(){},function(){});
	
	

	
}

function modificarDetailed(x/*eventName,category,author,dateInit,image*/){
	alert(x);
	/*var documento= $("#detailedform");
	documento.find('input[name="eventname"]').val(eventName);
	documento.find('input[name="category"]').val(category);
	documento.find('input[name="author"]').val(author);
	documento.find('input[name="dateInit"]').val(dateInit);*/
	//documento.find('img[name="eventImage"]').src(image);
	
	
	
}
	

function insertDetailedEvent(parent,event) {

	parent.append('<!-- Features Section -->\
        <div class="row" > \
			<form id="' + "detailedform" + '">\
            <div class="col-lg-12">\
                <h2 class="page-header"><input type="text" name="eventname" value="'+event.eventName+'"/></h2>\
            </div>\
           <div class="col-md-6">\
                <ul>\
					<li><strong>Categoría: </strong><input type="text" name="category" value="'+event.category+'"/></li>\
					<li><strong>Creador: </strong><input type="text" name="author" value="'+event.author+'"/></li>\
                    <li><strong>Fecha de Inicio: </strong><input type="text" name="dateInit" value="'+event.dateInit+'"/></li>\
                </ul>\
                <p>'+event.description+'</p>\
        		</form>\
            </div>\
            <div class="col-md-4 .col-md-offset-4">\
                <img name="eventImage" class="img-responsive" src="'+event.image+'" alt="">\
            </div>\
        </div>\
        <!-- /.row -->')
        
}

function insertRecommended(parent,events) {
	var hola = events[0].author;
	parent.append('<div class="row">\
            <div class="col-lg-12">\
            <h2 class="page-header">Eventos recomendados</h2>\
        </div>\
        <div class="col-md-3 col-sm-6">\
                <img class="img-responsive img-portfolio img-hover" src="'+ events[0].image+'" alt="" onclick="modificarDetailed(hola)">\
        </div>\
        <div class="col-md-3 col-sm-6">\
                <img class="img-responsive img-portfolio img-hover" src="'+ events[1].image+'" alt="" onclick="modificarDetailed('+events[1].nameEvent+','+events[1].category+','+events[1].author+','+events[1].dateInit+','+events[1].image+');">\
        </div>\
        <div class="col-md-3 col-sm-6">\
            <a href="" onclick="modificarDetailed('+events[2].nameEvent+','+events[2].category+','+events[2].author+','+events[2].dateInit+','+events[2].image+');">\
                <img class="img-responsive img-portfolio img-hover" src="'+ events[2].image+'" alt="">\
            </a>\
        </div>\
        <div class="col-md-3 col-sm-6">\
                <img class="img-responsive img-portfolio img-hover" src="'+ events[3].image+'" alt="" onclick="modificarDetailed('+events[3].nameEvent+','+events[3].category+','+events[3].author+','+events[3].dateInit+','+events[3].image+');">\
        </div>\
    </div>')
}