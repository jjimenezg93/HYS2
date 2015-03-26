function listEvent(done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/event',
		type: 'GET'
	})
	.done(done)
	.fail(fail)
	.always(always);
}


function addEvent(event, done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/event',
		type: 'POST',
		data: event
	})
	.done(done)
	.fail(fail)
	.always(always);
}

function modifyEvent(event, done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/event/' + event.id,
		type: 'PUT',
		data: event
	})
	.done(done)
	.fail(fail)
	.always(always);
}

function deleteEvent(id, done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/event/' + id,
		type: 'DELETE',
	})
	.done(done)
	.fail(fail)
	.always(always);
}