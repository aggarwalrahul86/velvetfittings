
var metricsVldCntnr= {};
metricsVldCntnr.ct_ph_57_50=									//D&D -- Planning Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_7", 
									   msg : "Planned Effort should be greater than zero for Planning Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_7");
									   }
								},
								{
									   id : "metricsParam_9", 
									   msg : "Activity Effort should be greater than zero for Planning Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_9");
									   }
								}
		                       ]
};

metricsVldCntnr.ct_ph_57_51=									//D&D -- Requirement Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_19", 
									   msg : "Planned Effort should be greater than zero for Requirement Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_19");
									   }
								},
								{
									   id : "metricsParam_21", 
									   msg : "Activity Effort should be greater than zero for Requirement Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_21");
									   }
								}
		                       ]
};
metricsVldCntnr.ct_ph_57_52=									//D&D -- Design Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_33", 
									   msg : "Planned Effort should be greater than zero for Design Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_33");
									   }
								},
								{
									   id : "metricsParam_35", 
									   msg : "Activity Effort should be greater than zero for Design Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_35");
									   }
								}
		                       ]
};
metricsVldCntnr.ct_ph_57_53=									//D&D -- CUT Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_47", 
									   msg : "Planned Effort should be greater than zero for Coding & Unit Testing Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_47");
									   }
								},
								{
									   id : "metricsParam_49", 
									   msg : "Activity Effort should be greater than zero for Coding & Unit Testing Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_49");
									   }
								}
		                       ]
};
metricsVldCntnr.ct_ph_57_54=									//D&D -- Testing Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_62", 
									   msg : "Planned Effort should be greater than zero for Testing Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_62");
									   }
								}
		                       ]
};
metricsVldCntnr.ct_ph_57_55=									//D&D -- UAT Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_78", 
									   msg : "Planned Effort should be greater than zero for UAT Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_78");
									   }
								}
		                       ]
};
metricsVldCntnr.ct_ph_57_56=									//D&D -- Deployment Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_92", 
									   msg : "Total Planned Effort should be greater than zero for Deployment Phase.",
									   checker : function(){
										   var totalVal = parseFloat($("#metricsParam_89").val())+parseFloat($("#metricsParam_90").val())+parseFloat($("#metricsParam_91").val())+parseFloat($("#metricsParam_92").val());
										   if(totalVal <= 0){
											   return false;
										   }
										   else{
											   return true;
										   }
									   }
								},
								{
									   id : "metricsParam_96", 
									   msg : "Total Actual Effort should be greater than zero for Deployment Phase.",
									   checker : function(){
										   var totalVal = parseFloat($("#metricsParam_93").val())+parseFloat($("#metricsParam_94").val())+parseFloat($("#metricsParam_95").val())+parseFloat($("#metricsParam_96").val());
										   
										   if(totalVal <= 0){
											   return false;
										   }
										   else{
											   return true;
										   }
									   }
								}
		                       ]
};

metricsVldCntnr.ct_ph_60_99=									//Small Project
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_180", 
									   msg : "Planned Effort should be greater than zero for Small Projects.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_180");
									   }
								},
								{
									   id : "metricsParam_211", 
									   msg : "Total Actual Effort should be greater than zero for Small Projects.",
									   checker : function(){
										   var totalVal = parseFloat($("#metricsParam_206").val())+parseFloat($("#metricsParam_207").val())
										   					+parseFloat($("#metricsParam_208").val())+parseFloat($("#metricsParam_209").val())+parseFloat($("#metricsParam_210").val())
										   					+parseFloat($("#metricsParam_211").val())/*parseFloat($("#metricsParam_205").val())+parseFloat($("#metricsParam_212").val())*/;
										   //alert(totalVal);
										   if(totalVal <= 0){
											   return false;
										   }
										   else{
											   return true;
										   }
									   }
								}
		                       ]
};

metricsVldCntnr.ct_ph_59_66=									//Testing -- Test Planning Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_130", 
									   msg : "Planned Effort should be greater than zero for Test Planning Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_130");
									   }
								}
		                       ]
};
metricsVldCntnr.ct_ph_59_67=									//Testing -- Test Preparation Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_142", 
									   msg : "Planned Effort should be greater than zero for Test Preparation Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_142");
									   }
								}
		                       ]
};
metricsVldCntnr.ct_ph_59_105=									//Testing -- Test Execution Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_158", 
									   msg : "Planned Effort should be greater than zero for Test Execution Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_158");
									   }
								}
		                       ]
};
metricsVldCntnr.ct_ph_59_106=									//Testing -- UAT Support Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_171", 
									   msg : "Planned Effort should be greater than zero for UAT Support Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_171");
									   }
								}
		                       ]
};


metricsVldCntnr.ct_ph_131_121=									//D&D Truncated -- Planning Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_239", 
									   msg : "Planned Effort should be greater than zero for Planning Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_239");
									   }
								},
								{
									   id : "metricsParam_241", 
									   msg : "Activity Effort should be greater than zero for Planning Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_241");
									   }
								}
		                       ]
};

metricsVldCntnr.ct_ph_131_122=									//D&D Truncated -- Requirement Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_251", 
									   msg : "Planned Effort should be greater than zero for Requirement Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_251");
									   }
								},
								{
									   id : "metricsParam_253", 
									   msg : "Activity Effort should be greater than zero for Requirement Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_253");
									   }
								}
		                       ]
};
metricsVldCntnr.ct_ph_131_123=									//D&D Truncated -- Design Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_265", 
									   msg : "Planned Effort should be greater than zero for Design Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_265");
									   }
								},
								{
									   id : "metricsParam_267", 
									   msg : "Activity Effort should be greater than zero for Design Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_267");
									   }
								}
		                       ]
};
metricsVldCntnr.ct_ph_131_124=									//D&D Truncated -- CUT Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_279", 
									   msg : "Planned Effort should be greater than zero for Coding & Unit Testing Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_279");
									   }
								},
								{
									   id : "metricsParam_281", 
									   msg : "Activity Effort should be greater than zero for Coding & Unit Testing Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_281");
									   }
								}
		                       ]
};
metricsVldCntnr.ct_ph_131_125=									//D&D Truncated -- Testing Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_294", 
									   msg : "Planned Effort should be greater than zero for Testing Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_294");
									   }
								}
		                       ]
};
metricsVldCntnr.ct_ph_131_126=									//D&D Truncated -- UAT Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_310", 
									   msg : "Planned Effort should be greater than zero for UAT Phase.",
									   checker : function(){
										   return validateGreaterThanZero("metricsParam_310");
									   }
								}
		                       ]
};
metricsVldCntnr.ct_ph_131_127=									//D&D Truncated -- Deployment Phase
{
		requiredValidations : [],
		formatValidations : [],
		dataTypeValidations : [
								{
									   id : "metricsParam_324", 
									   msg : "Total Planned Effort should be greater than zero for Deployment Phase.",
									   checker : function(){
										   var totalVal = parseFloat($("#metricsParam_321").val())+parseFloat($("#metricsParam_322").val())+parseFloat($("#metricsParam_323").val())+parseFloat($("#metricsParam_324").val());
										   if(totalVal <= 0){
											   return false;
										   }
										   else{
											   return true;
										   }
									   }
								},
								{
									   id : "metricsParam_328", 
									   msg : "Total Actual Effort should be greater than zero for Deployment Phase.",
									   checker : function(){
										   var totalVal = parseFloat($("#metricsParam_325").val())+parseFloat($("#metricsParam_326").val())+parseFloat($("#metricsParam_327").val())+parseFloat($("#metricsParam_328").val());
										   
										   if(totalVal <= 0){
											   return false;
										   }
										   else{
											   return true;
										   }
									   }
								}
		                       ]
};
