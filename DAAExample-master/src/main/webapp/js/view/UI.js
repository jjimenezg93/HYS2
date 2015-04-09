/*$.getScript('js/dao/event.js', function() {
	listEvent("logon",function(events) {
		$.each(events, function(key, event) {
			insertDetailedEvent(parent,event);
		}
		)
	});*/S
function insertDetailedEvent(parent,event) {
	parent.append('<!-- Features Section -->\
        <div class="row">\
            <div class="col-lg-12">\
                <h2 class="page-header">AQUI IRIA EL NOMBRE DEL EVENTO</h2>\
            </div>\
            <div class="col-md-6">\
                <ul>\
					<li><strong>Categoría: </strong><a href="#">COSAAAAAAA</a></li>\
					<li><strong>Creador: </strong><a href="#">pepito</a></li>\
                    <li><strong>Fecha: </strong>AQUI IRIA LA FECHA</li>\
                    <li><strong>Tiempo restante: </strong>AQUI VA EL TIEMPO RESTANTE</li>\
                    <li><strong>Participantes: </strong>AQUI VA EL NUMERO DE PARTICIPANTES</li>\
                </ul>\
                <p>The Modern Business template by Start Bootstrap includes:\
						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corporis, omnis doloremque non cum id reprehenderit, quisquam totam aspernatur tempora minima unde aliquid ea culpa sunt. Reiciendis quia dolorum ducimus unde.</p>\
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