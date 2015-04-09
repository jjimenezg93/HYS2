//mcpaz y adri
function listEvent(login,done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/event/' + login,
		type: 'GET'
	})
	.done(done)
	.fail(fail)
	.always(always);
}


