var dataTableSortExtensions = {
	ddmmyyyyhhmm_hyphen : {
		identifier : "ddmmyyyyhhmm_hyphen",
		evaluate : function (value) {
	    	var x = 10000000000000;
	        if ($.trim(value).length>0) {
	        	var frDatea = $.trim(value).split(' ');
	            var frTimea = frDatea[1].split(':');
	            var frDatea2 = frDatea[0].split('-');
	            x = new Date(parseInt(frDatea2[2], 10), parseInt(frDatea2[1], 10) - 1, parseInt(frDatea2[0], 10), parseInt(frTimea[0], 10), parseInt(frTimea[1], 10)).getTime(); 
	        } else {
	            x = 10000000000000; // = l'an 1000 ...
	        }
	        //alert("x::::****::::"+x);
	        return x;
	    }
	},
	ddmmyyyy_hyphen : {
		identifier : "ddmmyyyy_hyphen",
		evaluate : function (value) {
	    	var x = 10000000000000;
	        if ($.trim(value).length>0) {
	            var frDatea2 = $.trim(value).split('-');
	            x = new Date(parseInt(frDatea2[2], 10), parseInt(frDatea2[1], 10) - 1, parseInt(frDatea2[0], 10)).getTime(); 
	        } else {
	            x = 10000000000000; // = l'an 1000 ...
	        }
	        //alert("x::::****::::"+x);
	        return x;
	    }
	},
	yyyymmdd_hyphen : {
		identifier : "yyyymmdd_hyphen",
		evaluate : function (value) {
	    	var x = 10000000000000;
	        if ($.trim(value).length>0) {
	            var frDatea2 = $.trim(value).split('-');
	            x = new Date(parseInt(frDatea2[0], 10), parseInt(frDatea2[1], 10) - 1, parseInt(frDatea2[2], 10)).getTime(); 
	        } else {
	            x = 10000000000000; // = l'an 1000 ...
	        }
	        //alert("x::::****::::"+x);
	        return x;
	    }
	},
	yyyymmddhhmmss_hyphen : {
		identifier : "yyyymmddhhmmss_hyphen",
		evaluate : function (value) {
	    	var x = 10000000000000;
	        if ($.trim(value).length>0) {
	        	var frDatea = $.trim(value).split(' ');
	            var frTimea = frDatea[1].split(':');
	            var frDatea2 = frDatea[0].split('-');
	            x = new Date(parseInt(frDatea2[0], 10), parseInt(frDatea2[1], 10) - 1, parseInt(frDatea2[2], 10), parseInt(frTimea[0], 10), parseInt(frTimea[1], 10), parseInt(frTimea[2], 10)).getTime(); 
	        } else {
	            x = 10000000000000; // = l'an 1000 ...
	        }
	        //alert("x::::****::::"+x);
	        return x;
	    }
	}
};

jQuery.extend( jQuery.fn.dataTableExt.oSort, {
    "ddmmyyyy_hyphen-asc" : function ( a, b ) {
    	//alert(a+"::"+b+"__________"+(dataTableSortExtensions.ddmmyyyy_hyphen.evaluate(a) - dataTableSortExtensions.ddmmyyyy_hyphen.evaluate(b)));
        return (dataTableSortExtensions.ddmmyyyy_hyphen.evaluate(a) - dataTableSortExtensions.ddmmyyyy_hyphen.evaluate(b));
    },
    "ddmmyyyy_hyphen-desc" : function ( a, b ) {
    	//alert(b+"::"+a+"__________::____"+(dataTableSortExtensions.ddmmyyyy_hyphen.evaluate(b) - dataTableSortExtensions.ddmmyyyy_hyphen.evaluate(a)));
        return (dataTableSortExtensions.ddmmyyyy_hyphen.evaluate(b) - dataTableSortExtensions.ddmmyyyy_hyphen.evaluate(a));
    }
} );

jQuery.extend( jQuery.fn.dataTableExt.oSort, {
    "ddmmyyyyhhmm_hyphen-asc" : function ( a, b ) {
    	//alert(a+"::"+b+"__________"+(dataTableSortExtensions.ddmmyyyyhhmm_hyphen.evaluate(a) - dataTableSortExtensions.ddmmyyyyhhmm_hyphen.evaluate(b)));
        return (dataTableSortExtensions.ddmmyyyyhhmm_hyphen.evaluate(a) - dataTableSortExtensions.ddmmyyyyhhmm_hyphen.evaluate(b));
    },
    "ddmmyyyyhhmm_hyphen-desc" : function ( a, b ) {
    	//alert(b+"::"+a+"__________::____"+(dataTableSortExtensions.ddmmyyyyhhmm_hyphen.evaluate(b) - dataTableSortExtensions.ddmmyyyyhhmm_hyphen.evaluate(a)));
        return (dataTableSortExtensions.ddmmyyyyhhmm_hyphen.evaluate(b) - dataTableSortExtensions.ddmmyyyyhhmm_hyphen.evaluate(a));
    }
} );

jQuery.extend( jQuery.fn.dataTableExt.oSort, {
    "yyyymmdd_hyphen-asc" : function ( a, b ) {
    	//alert(a+"::"+b+"__________"+(dataTableSortExtensions.ddmmyyyy_hyphen.evaluate(a) - dataTableSortExtensions.ddmmyyyy_hyphen.evaluate(b)));
        return (dataTableSortExtensions.yyyymmdd_hyphen.evaluate(a) - dataTableSortExtensions.yyyymmdd_hyphen.evaluate(b));
    },
    "yyyymmdd_hyphen-desc" : function ( a, b ) {
    	//alert(b+"::"+a+"__________::____"+(dataTableSortExtensions.ddmmyyyy_hyphen.evaluate(b) - dataTableSortExtensions.ddmmyyyy_hyphen.evaluate(a)));
        return (dataTableSortExtensions.yyyymmdd_hyphen.evaluate(b) - dataTableSortExtensions.yyyymmdd_hyphen.evaluate(a));
    }
} );

jQuery.extend( jQuery.fn.dataTableExt.oSort, {
    "yyyymmddhhmmss_hyphen-asc" : function ( a, b ) {
    	//alert(a+"::"+b+"__________"+(dataTableSortExtensions.ddmmyyyyhhmm_hyphen.evaluate(a) - dataTableSortExtensions.ddmmyyyyhhmm_hyphen.evaluate(b)));
        return (dataTableSortExtensions.yyyymmddhhmmss_hyphen.evaluate(a) - dataTableSortExtensions.yyyymmddhhmmss_hyphen.evaluate(b));
    },
    "yyyymmddhhmmss_hyphen-desc" : function ( a, b ) {
    	//alert(b+"::"+a+"__________::____"+(dataTableSortExtensions.ddmmyyyyhhmm_hyphen.evaluate(b) - dataTableSortExtensions.ddmmyyyyhhmm_hyphen.evaluate(a)));
        return (dataTableSortExtensions.yyyymmddhhmmss_hyphen.evaluate(b) - dataTableSortExtensions.yyyymmddhhmmss_hyphen.evaluate(a));
    }
} );