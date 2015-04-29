var userCity;
function getUserGeolocation(){
jQuery.ajax({
	url : '//freegeoip.net/json/',
	type : 'POST',
	dataType : 'jsonp',
	success : function(location) {
		// example where I update content on the page.
		jQuery('#city').html(location.city);
		// jQuery('#zipcode').html(location.zipcode);	OTRA OPCION, USANDO RANGO DE CERCANIA
		userCity = location.city;
	}
});
}

function init(parent){
	
	listEvent("Pablo",function(events){
		insertRecommended(parent,events);
		insertDetailedEvent(parent,events[1]);
		slider();
	},function(){},function(){});
	

	
}

function modificarDetailed(eventName, category, author, dateInit, place, image, description){
		
	var date = new Date(Number(dateInit)).toDateString();
	var documento= $("#detailedform");
	documento.find('input[name="eventname"]').val(eventName);
	documento.find('input[name="category"]').val(category);
	documento.find('input[name="author"]').val(author);
	documento.find('input[name="dateInit"]').val(date.toString());
	documento.find('input[name="place"]').val(place);
	document.getElementById('description').innerHTML = description;
	document["eventImage"].src = image;

	
	
	
}

function getGeolocation(parent){
	/*parent.append('<div>' + document.write(geoip_city()) + '</div>'
			)*/
	
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
                    <li><strong>Lugar: </strong><input type="text" name="place" value="'+event.place+'"/></li>\
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

	cadenaHtml ='\
		<div id="slider1_container" style="position: relative; top: 0px; left: 0px; width: 810px; height: 300px; background: #000; overflow: hidden; ">\
		<!-- Loading Screen -->\
			<div id="ContenedorEstilos" u="loading" style="position: absolute; top: 0px; left: 0px;">\
	        	<div id="Estilos1" style="filter: alpha(opacity=70); opacity:0.7; position: absolute; display: block;\
	            	background-color: #000000; top: 0px; left: 0px;width: 100%;height:100%;">\
	        	</div>\
	        	<div id="Estilos2" style="position: absolute; display: block; background: url(../img/loading.gif) no-repeat center center;\
	            	top: 0px; left: 0px;width: 100%;height:100%;">\
	        	</div>\
			</div>\
			<div id="contenedorDeEventos" u="slides" style="cursor: move; position: absolute; left: 0px; top: 0px; width: 600px; height: 300px;\
            overflow: hidden;">';
		
		
	for (eve in events){
	cadenaHtml+='	<div>\
						<a id="event'+eve+'" u="image" onclick="modificarDetailed(\''+events[eve].nameEvent+'\',\''+events[eve].category+'\',\''+events[eve].author+'\',\''+events[eve].dateInit+'\',\''+events[eve].place+'\',\''+events[eve].image+'\',\''+events[eve].description+'\');">\
						<img id="event'+eve+'" src="'+ events[eve].image+'" />\
						</a>\
                			<div id="event'+eve+'thumbcontainer" u="thumb">\
			                    <img id="event'+eve+'image" class="i" src="'+ events[eve].image+'" />\
			                    <div id="event'+eve+'event" class="t">'+events[eve].nameEvent +'</div>\
			                    <div id="event'+eve+'category" class="c">'+events[eve].category +'</div>\
			                </div>\
                    </div>';
				
	};

		
	cadenaHtml+='</div>\
		<div u="thumbnavigator" class="jssort11" style="left: 605px; top:0px;">\
			<!-- Thumbnail Item Skin Begin -->\
			<div u="slides" style="cursor: default;">\
        		<div u="prototype" class="p" style="top: 0; left: 0;">\
            		<div  u="thumbnailtemplate" class="tp">\
					</div>\
        		</div>\
			</div>\
		</div></div>';	
	
	parent.append(cadenaHtml);
}