
var loginValidationJSON= {
		requiredValidations : [
		                       	{id : "username"},
		                       	{id : "password"}
		                       	],
		formatValidations : [],
		dataTypeValidations : []

};

var addPartsValidationJSON= {
		requiredValidations : [
		                       	{id : "partsId"},
		                       	{id : "addedQnty"}
		                       	],
       	formatValidations : [
		                     {id : "addedQnty", format: "number"}                
							],
		dataTypeValidations : []

};

var deletePartsValidationJSON= {
		requiredValidations : [
		                       	{id : "partsId"},
		                       	{id : "deleteQnty"}
		                       	],
       	formatValidations : [
		                     {id : "deleteQnty", format: "number"}                
							],
		dataTypeValidations : []

};
var getOrderValidationJSON= {
		requiredValidations : [
		                       	{id : "orderId"}
		                       	],
       	formatValidations : [
							],
		dataTypeValidations : []

};
var generateBillValidationJSON= {
		requiredValidations : [
		                       	{id : "orderId"},
		                       	{id: "billDate"}
		                       	
		                       	],
       	formatValidations : [
       	                     {id : "billDate", format: "date"} 
							],
		dataTypeValidations : []

};

