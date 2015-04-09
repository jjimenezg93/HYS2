function init(parent){
	listEvent("logon",function(events){
		$.each(events, function(key, event) {
			insertDetailedEvent(parent,event);
		});
		
	},function(){},function(){});
	
	modificarDetailed("hola");
	
}

function modificarDetailed(event){
	
	var cosa= $("#detailed");

	cosa.find('[name="hola"]').val("huehuheuheuhuehueuh");
	
	
}
	

function insertDetailedEvent(parent,event) {

	parent.append('<!-- Features Section -->\
        <div class="row">\
            <div class="col-lg-12">\
                <h2 class="page-header">'+ event.nameEvent+'</h2>\
            </div>\
            <div id="detailed" class="col-md-6">\
                <ul>\
					<li><strong>Categoría: </strong><div value="' + event.category + '"</div><a href="#"></a></li>\
					<li><strong>Creador: </strong>'+event.category+'<a href="#"></a></li>\
                    <li><strong>Fecha de Inicio: </strong>'+event.dateInit+'</li>\
                    <li><strong>Tiempo restante: </strong>'+"huehuehue"+'</li>\
                    <li><strong>Participantes: </strong>'+"asdasdasd"+'</li>\
                </ul>\
                <p>'+event.description+'</p>\
            </div>\
            <div class="col-md-4 .col-md-offset-4">\
                <img class="img-responsive" src="http://placehold.it/700x450" alt="">\
            </div>\
        </div>\
        <!-- /.row -->')
}

function insertRecommended(parent) {
	parent.append('<div class="row">\
            <div class="col-lg-12">\
            <h2 class="page-header">Eventos recomendados</h2>\
        </div>\
        <div class="col-md-3 col-sm-6">\
            <a href="portfolio-item.html">\
                <img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">\
            </a>\
        </div>\
        <div class="col-md-3 col-sm-6">\
            <a href="portfolio-item.html">\
                <img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">\
            </a>\
        </div>\
        <div class="col-md-3 col-sm-6">\
            <a href="portfolio-item.html">\
                <img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">\
            </a>\
        </div>\
        <div class="col-md-3 col-sm-6">\
            <a href="portfolio-item.html">\
                <img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">\
            </a>\
        </div>\
    </div>')
}