$(document).ready(function(){
	theme = getDemoTheme();
	
	/* Setting style on the buttons - Start */	
	$("#saveButton").jqxButton({ theme: theme });
	$("#resetAdd").jqxButton({ theme: theme });
	$("#fetch").jqxButton({ theme: theme });
	$("#deleterowbutton").jqxButton({ theme: theme });
	/* Setting style on the buttons - End */
	
	/* Enabling form validation - Start */
	$('#productAddForm input').attr('class', 'required');
	$("#productAddForm").validate();
	/* Enabling form validation - End */


	/* Add multiple contacts for Customer - fetch button event - End */

	/* Accordion for outer most area */
	$( "#accordionAll" ).accordion({
		collapsible: true,
		/* active: false, */
		active:0,
		heightStyle: "content",
		activate: function( event, ui ) {
			$("#productShortName").focus();
		}
	});

	/* Accordions for inside add customer */
	$( "#accordionAdd" ).accordion({
		collapsible: true,
		active: 0
	});


	/* Fetch all Customer - fetch button event - Start */
	$( "#fetch" ).click(function() {
		fetchAllRecords();
	});
	
	var fetchAllRecords = function (){
		$("#jqxgrid").remove();
		
		$("#fetch").attr("disabled","disabled");
		$("#waitText1").text('Please wait ... ');
		$("#fetch").css('cursor','progress');

		$.ajax({
			type: 'GET',
			url: contextPath+'/product/list.view',
			dataType: 'json',
			success : function(responseData, textStatus, jqXHR) {
				$('<div id="jqxgrid"></div>').appendTo($("#jqxWidget"));

				$("#fetch").removeAttr("disabled","disabled");
				$("#waitText1").text('');
				$("#fetch").css('cursor','pointer');

				var data = new Array();

				for (x in responseData){
					var row = {};
					row["prodictId"] = responseData[x].prodictId;
					row["productShortName"] = responseData[x].productShortName;
					row["productName"] = responseData[x].productShortName;
					row["productPrice"] = responseData[x].productShortName;
					data[x] = row;
				}

				var source = {
						localdata: data,
						datatype: "array"
				};

				var initrowdetails = function (index, parentElement, gridElement, datarecord) {
					var tabsdiv = null;
				
					tabsdiv = $($(parentElement).children()[0]);

				
						$(contacts).append(contactContainer);

						$(tabsdiv).jqxTabs({ width: 600, height: 170, theme: theme });
					
				};
				var dataAdapter = new $.jqx.dataAdapter(source);
				var editrow = -1;
//				$("input").jqxInput({ theme: theme });

				$("#jqxgrid").jqxGrid({
					width: 1110,
					autoheight: true,
					source: dataAdapter,
					theme: theme,
					rowdetails: true,
					columnsresize: true,
					autoshowcolumnsmenubutton: false,
					sortable: true,
					rowdetailstemplate: { rowdetails: "<div style='margin: 10px;'><ul style='margin-left: 30px;'><li>Credit Card</li><li>Contacts</li></ul><div class='creditCard'></div><div class='contact'></div></div>", rowdetailsheight: 200 },
					ready: function () {
						$("#jqxgrid").jqxGrid('sortby', 'customerName', 'asc');
					},
					initrowdetails: initrowdetails,
					columns: [
					          { text: 'Product Short Name', datafield: 'productShortName', width: 180 },
					          { text: 'Product Name', datafield: 'productName', width: 140 },
					          { text: 'Product Price', datafield: 'productPrice', width: 100, filtertype: 'number' },
					          { text: 'Edit', datafield: 'Edit', columntype: 'button', sortable: false, cellsrenderer: function () {
					        	  return "Edit";
						          }, buttonclick: function (row) {
						        	  editrow = row;
						        	  var offset = $("#jqxgrid").offset();
						        	  $("#popupWindow").jqxWindow({ position: { x: parseInt(offset.left) + 160, y: parseInt(offset.top) + 60 }, height: 252});
	
						        	  dataRecord_row = $("#jqxgrid").jqxGrid('getrowdata', editrow);
	
						        	  $("#ed_productId").val(dataRecord_row.productId);
						        	  $("#ed_productShortName").val(dataRecord_row.productShortName);
						        	  $("#ed_productName").val(dataRecord_row.productName);
						        	  $("#ed_productPricee").val(dataRecord_row.productPrice);

						        
	
						        	  $("#editTable").append(tempDiv);
						        	  $("#editTable").append('<tr class="ed_temp_button"><td colspan="7" align="right"><p id="waitText4"></p></td><td style="padding-top: 10px;" align="right"><input style="margin-right: 5px;" type="button" id="ed_save" value="Save" /><input id="Cancel" type="button" value="Cancel" /></td></tr>');
						        	  extraHeight = extraHeight + 40;
						        	  
						        	  // show the popup window.
						        	  $("#popupWindow").jqxWindow('open');
						        	  
						        	  var popupHeight = $('#popupWindow').jqxWindow('height');
						        	  $('#popupWindow').jqxWindow({height:popupHeight+extraHeight});
						        	  $("#popupWindow").jqxWindow({
						        		  width: 800, resizable: true, theme: theme, isModal: true, autoOpen: false, cancelButton: $("#Cancel"), modalOpacity: 0.01, showCloseButton: true           
						        	  });
						        	  $("#popupWindow").on('open', function () {
						        		  $("#ed_name").jqxInput('selectAll');
						        	  });						        	  
									  $("#Cancel").jqxButton({ theme: theme });
									  $("#ed_save").jqxButton({ theme: theme });
									  
									  $("#ed_save").on('click', editSave);
						          }
					          }
					   ]
				});
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log('error:', errorThrown);
				$("#fetch").removeAttr("disabled","disabled");
				$("#waitText1").text('Error: '+textStatus);
				$("#fetch").css('cursor','pointer');
				hideAndFade("#waitText1");
			}
		});
	};
	/* Fetch all Customer - fetch button event - End */

	/* Edit a Customer - ed_save button event - Start */
	var editSave = function(){
		
		$("#ed_save").attr("disabled","disabled");
		$("#waitText4").text('Please wait ... ');
		$("#ed_save").css('cursor','progress');
		
		var arr = [];
	

		var formElements = new Object({
			productId : $("#ed_productId").val(),
			productShortName: $("#ed_productShortName").val(), 
			productName: new Date($("#ed_productName").val()),
			productPrice: new Date($("#ed_productPrice").val()),
		});

		$.ajax({
			type: 'POST',
			url: contextPath+'/product/update.do',
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(formElements),
			success : function(data, textStatus, jqXHR) {
				$("#ed_save").removeAttr("disabled","disabled");
				$("#waitText4").text('Save successful...');
				hideAndFade("#waitText4");
				$("#ed_save").css('cursor','pointer');
				
                $("#popupWindow").jqxWindow('hide');
                fetchAllRecords();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log('error:', errorThrown);
				$("#ed_save").removeAttr("disabled","disabled");
				$("#waitText4").text('Save failed : '+errorThrown);
				hideAndFade("#waitText4");
				$("#ed_save").css('cursor','pointer');
			}
		});
	};

	/* Edit a Customer - ed_save button event - Start */

	/* Delete selected row - Start */
	$("#deleterowbutton").on('click', function () {
		$("#deleterowbutton").attr("disabled","disabled");
		$("#waitText3").text('Please wait ... ');
		$("#deleterowbutton").css('cursor','progress');
		
		var id = null;
		var customerId = null;
		try {
			var selectedrowindex = $("#jqxgrid").jqxGrid('getselectedrowindex');
			var rowscount = $("#jqxgrid").jqxGrid('getdatainformation').rowscount;
			if (selectedrowindex >= 0 && selectedrowindex < rowscount) {
				id = $("#jqxgrid").jqxGrid('getrowid', selectedrowindex);
			}
		}catch(err){}

		if (id != null){
			customerId = $('#jqxgrid').jqxGrid('getrowdata', id).customerId;	

			$.ajax({
				type: 'GET',
				url: contextPath+'/product/remove.do?productId='+productId,
				dataType: 'json',
				success : function(responseData, textStatus, jqXHR) {
					var commit = $("#jqxgrid").jqxGrid('deleterow', id);
					$("#deleterowbutton").removeAttr("disabled","disabled");
					$("#waitText3").text('Row deleted successfully');
					$("#deleterowbutton").css('cursor','pointer');
					
					hideAndFade("#waitText3");
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log('error: '+textStatus);
					$("#deleterowbutton").removeAttr("disabled","disabled");
					$("#waitText3").text('Error: '+textStatus);
					hideAndFade("#waitText3");
					$("#deleterowbutton").css('cursor','pointer');
				}
			});
		} else {
			$("#deleterowbutton").removeAttr("disabled","disabled");
			$("#waitText3").text('Please select a row');
			hideAndFade("#waitText3");
			$("#deleterowbutton").css('cursor','pointer');
		}
	});
	/* Delete selected row - End */

	/* Add Customer - submit button event - Start */
	$("#saveButton").click(function() {
		if (!$("#productAddForm").validate().form()){
			return false;
		}
		
		$("#saveButton").attr("disabled","disabled");
		$("#waitText2").text('Please wait ... ');
		$("#saveButton").css('cursor','progress');

		var arr = [];

		
		var formElements = new Object({
			productId : 1,
			productShortName: $("#productShortName").val(), 
			productName: $("#productName").val(), 
			productPrice: $("#productPrice").val(), 
			productImagePath: $("#productImagePath").val(), 
		});

		

		
		
		$.ajax({
			type: 'POST',
			url: contextPath+'/product/add.do',
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(formElements),
			success : function(data, textStatus, jqXHR) {
				$("#productAddForm")[0].reset();
				
				$("#saveButton").removeAttr("disabled","disabled");
				$("#saveButton").css('cursor','pointer');
				$("#waitText2").text('Save successful...');
				
				fetchAllRecords();
				
				hideAndFade("#waitText2", activateFetchArea);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log('error:', errorThrown);
				$("#saveButton").removeAttr("disabled","disabled");
				$("#waitText2").text('Save failed : '+errorThrown);
				$("#saveButton").css('cursor','pointer');
				
				hideAndFade("#waitText2");
			}
		});
	});
	/* Add Product - submit button event - End */
});

/* Hide slowly effect */
var hideAndFade = function(selector, callback){
	setTimeout(function() {
		$(selector).text('');
		if (callback && typeof(callback) === "function") {  
	        callback();
	    }
	}, 2000);
};

/* Activate the fetch area */
var activateFetchArea = function(){
	$( "#accordionAll" ).accordion( {active:0} );	
}
