/**
 * Get the form object and create a key value object for all the fields present
 * in the form.
 */
jQuery.fn.extend({
	serializeCustomPropertyArray : function() {
		return this.map(function() {
			return this.elements ? jQuery.makeArray(this.elements) : this;})
				.filter(function() {
//					return jQuery(this).attr('name')
					 return !jQuery(this).attr('jsonIgnore')
							&& (this.checked|| /select|textarea/i
									.test(this.nodeName) || /text|hidden|password|search/i.test(this.type));
					}).map(function(i, elem) {
						var val = jQuery(this).val();
						return val == null ? null : jQuery
							.isArray(val) ? jQuery.map(val,
					function(val, i) {
					return {
						name : jQuery(elem).attr('name'),
						value : val	};}) : {
						name : jQuery(elem).attr('name'),
						value : val};}).get();
			},
	/**
	 * Get the key value map from the serializeCustomPropertyArray()
	 * method and call convertJson to convert the same into JSON String
	 * 
	 * @returns Receive JSON object from convertToJson() method and the
	 *          convert the same into JSON String with help of
	 *          Json.Stringify method
	 */
	serializeToJSON : function() {
		var objectMap = this.serializeCustomPropertyArray();
		var jsonObjectParent = convertToJson(objectMap);
		var jsonObject = createJsonForDataTable(jsonObjectParent);
			return JSON.stringify(jsonObject);
		}
	});

	/**
	 * 
	 * @param keyValueMap
	 *            contains all the field of the form in key value pair
	 * @returns {Object} return the JSON object
	 */
	function convertToJson(keyValueMap) {
		var objectJson = new Object;
		jQuery.each(keyValueMap,function() {
			var jsonArr = createJsonArrayForMultiSelect(this.name,keyValueMap);
			if (jsonArr != null) {
				objectJson[this.name] = jsonArr;
			} else if($('[name='+this.name+']').attr('convertArray')!=null 
				&& $('[name='+this.name+']').attr('convertArray')!='undefined'){
				var arrayObj = new Array();
				arrayObj.push((this.value !== null) ? $.trim(this.value): 'null');
				objectJson[this.name] = arrayObj;
			} else {
				objectJson[this.name] = (this.value !== null) ? $.trim(this.value): 'null';
			}
		});
		return objectJson;
	}

	function createJsonForDataTable(jsonObject) {
			var childObj = null;
			var isFound = 0;
			var tables = $('table');
			if (tables != null) {
			tables.each(function(counter, tableObj) {
				if (tableObj.attributes['dataTable'] != null
						&& tableObj.attributes['dataTable'] != 'undefined') {
				var ObjArr = new Array();
				var dataTableName = tableObj.attributes['dataTable'].value;
				var rows = $('table[dataTable=' + dataTableName+ '] tr:gt(0)');
			if (rows != null) {
				rows.each(function(index) {
				childObj = new Object();
				$(this).children("td").each(function() {
					
					//alert($(this).attr('name')+ ": "+ $(this).text());
				if ($(this).attr('name')!=null 
						&& $(this).attr('name')!='undefined' 
						&& $(this).attr('name')!="") {
					
				
				var tdName = $(this).attr('name');
				isFound = 1;	
				if(($(this).parent().find("td[name="+tdName+"]").length>1)
						|| ($(this).attr('convertArray')!=null 
							&& $(this).attr('convertArray')!='undefined')){

					var multipleTdObj = $(this).parent().find("td[name="+tdName+"]");
					var tdArray = new Array();
					$.each(multipleTdObj,function(id,tdElem) {
						if($(this).children().length>0){
							  fieldType= $(this).children().get(0).type;
							  fieldObj = $(this).children().get(0);
								if(fieldType == 'text'){
									tdArray.push($.trim(fieldObj.value));
								}else if(fieldType == 'select-one'){
									tdArray.push($.trim($.trim(fieldObj.value)));
								}else if(fieldType == 'checkbox' &&  $(fieldObj).is(':checked')){
									tdArray.push( $.trim(fieldObj.value));
								}else if(fieldType == 'radio' &&  $(fieldObj).is(':checked')){
									tdArray.push($.trim(fieldObj.value));
								}else if(fieldType == 'textarea'){
									tdArray.push($.trim(fieldObj.value));
								}
							}else {
								tdArray.push($.trim($(this).text()));
							}
					});
					/*
					 * added the td object array into the child object
					 */
					childObj[tdName] = tdArray;
				}else{	

					var fieldType ="";
					var fieldObj = null;
				if($(this).children().length>0){
				  fieldType= $(this).children().get(0).type;
				  fieldObj = $(this).children().get(0);
					if(fieldType == 'text'){
						childObj[$(this).attr('name')] = $.trim(fieldObj.value);
					}else if(fieldType == 'select-one'){
						childObj[$(this).attr('name')] = $.trim(fieldObj.value);
					}else if(fieldType == 'checkbox' &&  $(fieldObj).is(':checked')){
						childObj[$(this).attr('name')] = $.trim(fieldObj.value);
					}else if(fieldType == 'radio' &&  $(fieldObj).is(':checked')){
						childObj[$(this).attr('name')] = $.trim(fieldObj.value);
					}else if(fieldType == 'textarea'){
						childObj[$(this).attr('name')] = $.trim(fieldObj.value);
					}
				}else {
					  childObj[$(this).attr('name')] = $.trim($(this).text());
					}
				}
				}
			});
				if (isFound ==1) {
					ObjArr.push(childObj);
					isFound = 0;
				}
			 });
		   }
				if(ObjArr.length>0){
					jsonObject[dataTableName]=ObjArr;
				}
			}
		});
	 }
	return jsonObject;
  }


/**
 * If multiple values exist against a single name then convert the same into a
 * array else return null.
 * 
 * @param key
 *            field name
 * @param keyValueMap
 *            all fields in the form in key value pair
 * @returns JSON object list or null
 */
	function createJsonArrayForMultiSelect(key, keyValueMap) {
		var counter = 0;
		var jsonArr = new Array();
		$.each(keyValueMap, function() {
			if (this.name == key) {
				jsonArr.push((this.value !== null) ? this.value : 'null');
				counter = counter + 1;
			}
		});
		if ((checkIsArrayNeeded(key)) || (counter > 1 && jsonArr.length > 1)) {
			return jsonArr;
		}
		return null;
	}


	function checkIsArrayNeeded(keyName){
	    if($('[name='+keyName+']').length >0){
		var fieldType = $('[name='+keyName+']').get(0).type;
		if(fieldType=='select-multiple'){
			return true;
	       }else  if((fieldType=='checkbox') && ($('[name='+keyName+']').length>0)){
			return true;
	     }
	  }
	 return false;
	}