$(document).ready(function(){
	theme = getDemoTheme();
	
	/* Setting style on the buttons - Start */	
	$("#saveButton").jqxButton({ theme: theme });
	$("#resetAdd").jqxButton({ theme: theme });
	$("#fetch").jqxButton({ theme: theme });
	$("#deleterowbutton").jqxButton({ theme: theme });
	$("#searchButton").jqxButton({ theme: theme });
	
	$("#searchCustomerName").jqxInput({placeHolder: "Customer Name", height: 25, width: 200, minLength: 1, theme: theme });
	$("#searchHouseNo").jqxInput({placeHolder: "House No", height: 25, width: 200, minLength: 1, theme: theme });
	$("#searchStreet").jqxInput({placeHolder: "Street", height: 25, width: 200, minLength: 1, theme: theme });
	/* Setting style on the buttons - End */
	
	/* Enabling form validation - Start */
	$('#customerAddForm input').attr('class', 'required');
	$("#customerAddForm").validate();
	/* Enabling form validation - End */

	/* Add multiple contacts for Customer - fetch button event - Start */
	$("#addRow").click(function(){
		var row = $("#contactTable tr:first").html();
		rowCount=rowCount+1;
		var rowId = "rowList"+rowCount;
		$("#contactTable tr:last").after('<tr id="'+rowId+'">'+row+'</tr>');
		$("#contactDetails").css( "height", "100%" );
	});
	/* Add multiple contacts for Customer - fetch button event - End */

	/* Accordion for outer most area */
	$( "#accordionAll" ).accordion({
		collapsible: true,
		/* active: false, */
		active:0,
		heightStyle: "content",
		activate: function( event, ui ) {
			$("#custName").focus();
		}
	});

	/* Accordions for inside add customer */
	$( "#accordionAdd" ).accordion({
		collapsible: true,
		active: 0
	});

	/* Plugin for Date picker */
	$( "#custMemberSince, #cardExpDate, #ed_memberSince, #ed_expDate" ).datepicker();

	/* Fetch all Customer - fetch button event - Start */
	$( "#fetch" ).click(function() {
		fetchAllRecords('');
	});
	
	$( "#searchButton" ).click(function() {
		fetchAllRecords('search');
	});
	
	var fetchAllRecords = function (resulttype){
		$("#jqxgrid").remove();
		
		$("#fetch").attr("disabled","disabled");
		$("#waitText1").text('Please wait ... ');
		$("#fetch").css('cursor','progress');

		var fetchURL = '';
		
		if (resulttype=='search') {
			var customerName = $("#searchCustomerName").val();
			var houseNumber = $("#searchHouseNo").val();
			var street = $("#searchStreet").val();
			
			fetchURL=contextPath+'/customer/search.view?customerName='+customerName+'&houseNumber='+houseNumber+'&street='+street;
		} else {
			fetchURL=contextPath+'/customer/list.view';
		}
		
        var source = {
            datatype: "json",
            datafields: [
                { name: 'customerId' },
                { name: 'customerName' },
                { name: 'memberSince', type:'date' },
                { name: 'balance' },
                { name: 'addressId', map: 'customerAddress>addressId' },
                { name: 'houseNumber', map: 'customerAddress>houseNumber' },
                { name: 'street', map: 'customerAddress>street' },
                { name: 'city', map: 'customerAddress>city' },
                { name: 'state', map: 'customerAddress>state' },
                { name: 'country', map: 'customerAddress>country' },
                { name: 'creditCardId', map: 'defaultCard>creditCardId' },
                { name: 'cardNumber', map: 'defaultCard>cardNumber' },,
                { name: 'securityCode', map: 'defaultCard>securityCode' },
                { name: 'expDate', type:'date', map: 'defaultCard>expDate' },
                { name: 'cardType', map: 'defaultCard>cardType' },
                { name: 'contacts' },
            ],
			url: fetchURL,
            cache: false,
            beforeprocessing: function (data) {
				source.totalrecords = data.totalItems;
//				source.totalrecords = 100;
            }
        };
        
        var dataAdapter = new $.jqx.dataAdapter(source);
		
		$('<div id="jqxgrid"></div>').appendTo($("#jqxWidget"));
		$("#fetch").removeAttr("disabled","disabled");
		$("#waitText1").text('');
		$("#fetch").css('cursor','pointer');
		var editrow = -1;
		
		var initrowdetails = function (index, parentElement, gridElement, datarecord) {
			var tabsdiv = null;
			var contacts = null;
			var creditCard = null;
			tabsdiv = $($(parentElement).children()[0]);
			
			if (tabsdiv != null) {
				creditCard = tabsdiv.find('.creditCard');
				contacts = tabsdiv.find('.contact');

				var creditCardContainer = $('<div style="white-space: normal; margin: 5px;">'
						+'<table>'
						+ '	<tr><td><label>Card Number</label></td><td> : </td><td>'+datarecord.cardNumber+'</td></tr>'
						+ '	<tr><td><label>Security code</label></td><td> : </td><td>'+datarecord.securityCode+'</td></tr>'
						+ '	<tr><td><label>Exp. Date</label></td><td> : </td><td>'+new Date(datarecord.expDate).toLocaleDateString()+'</td></tr>'
						+ '	<tr><td><label>Card Type</label></td><td> : </td><td>'+datarecord.cardType+'</td></tr>'
						+ '</table>'
						+ ' </div>');

				$(creditCard).append(creditCardContainer);

				var tempDiv = '<table style="width:100%">'
					+ '<tr><th>Phone Number</th><th>Phone Type</th><th>Contact Type</th><th>Email Id</th></tr>';

				var tempContactsArray = datarecord.contacts;
				for (x in tempContactsArray){
					tempDiv = tempDiv + '<tr><td>'+tempContactsArray[x].phoneNumber+'</td><td>'+tempContactsArray[x].phoneType+'</td><td>'+tempContactsArray[x].contactType+'</td><td>'+tempContactsArray[x].emailId+'</td></tr>';
				}
				tempDiv = tempDiv + '</table>';

				var contactContainer = $('<div style="white-space: normal; margin: 5px;">' + tempDiv + '</div>');
				$(contacts).append(contactContainer);

				$(tabsdiv).jqxTabs({ width: 600, height: 170, theme: theme });
			}
		};
		
		$("#jqxgrid").jqxGrid({
			/* Setting up Grid properties - START */
			width: 1110,
			autoheight: true,
			source: dataAdapter,
			theme: theme,
			rowdetails: true,
			columnsresize: true,
			autoshowcolumnsmenubutton: false,
			sortable: true,
			
			pageable: true,
			virtualmode: true,
			pagesize : 20,
			rendergridrows: function (params) {
                return params.data;
            },
            /* Setting up Grid properties - END */
            rowdetailstemplate: { rowdetails: "<div style='margin: 10px;'><ul style='margin-left: 30px;'><li>Credit Card</li><li>Contacts</li></ul><div class='creditCard'></div><div class='contact'></div></div>", rowdetailsheight: 200 },
			ready: function () {
				$("#jqxgrid").jqxGrid('sortby', 'customerName', 'asc');
			},
			initrowdetails: initrowdetails,
			columns: [
			          { text: 'Name', datafield: 'customerName', width: 180 },
			          { text: 'Member Since', datafield: 'memberSince', width: 140, filtertype: 'date', cellsformat: 'd' },
			          { text: 'Balance', datafield: 'balance', width: 100, filtertype: 'number' },
			          { text: 'House No', datafield: 'houseNumber', width: 100, filtertype: 'number' },
			          { text: 'Street', datafield: 'street', width: 180 },
			          { text: 'City', datafield: 'city', width: 100 },
			          { text: 'State', datafield: 'state', width: 100 },
			          { text: 'Country', datafield: 'country', width: 100 },
			          { text: 'Edit', datafield: 'Edit', columntype: 'button', sortable: false, cellsrenderer: function () {
			        	  return "Edit";
				          }, buttonclick: function (row) {
				        	  editrow = row;
				        	  var offset = $("#jqxgrid").offset();
				        	  $("#popupWindow").jqxWindow({ position: { x: parseInt(offset.left) + 160, y: parseInt(offset.top) + 60 }, height: 252});

				        	  dataRecord_row = $("#jqxgrid").jqxGrid('getrowdata', editrow);

				        	  $("#ed_customerId").val(dataRecord_row.customerId);
				        	  $("#ed_name").val(dataRecord_row.customerName);
				        	  $("#ed_memberSince").val(new Date(dataRecord_row.memberSince).toLocaleDateString());
				        	  $("#ed_balance").val(dataRecord_row.balance);

				        	  $("#ed_addressId").val(dataRecord_row.addressId);
				        	  $("#ed_houseNumber").val(dataRecord_row.houseNumber);
				        	  $("#ed_street").val(dataRecord_row.street);
				        	  $("#ed_city").val(dataRecord_row.city);
				        	  $("#ed_state").val(dataRecord_row.state);
				        	  $("#ed_country").val(dataRecord_row.country);

				        	  $("#ed_creditCardId").val(dataRecord_row.creditCardId);
				        	  $("#ed_cardNum").val(dataRecord_row.cardNumber);
				        	  $("#ed_securityCd").val(dataRecord_row.securityCode);
				        	  $("#ed_expDate").val(new Date(dataRecord_row.expDate).toLocaleDateString());
				        	  $("#ed_cardType").val(dataRecord_row.cardType);


				        	  var tempContactsArray = dataRecord_row.contacts;
				        	  var tempDiv='';
				        	  var extraHeight=0;
				        	  $(".ed_temp_contact").remove();
				        	  $(".ed_temp_button").remove();
				        	  
				        	  if (tempContactsArray.length == 0){
				        		  tempDiv = tempDiv + '<tr id="ed_rowList'+x+'" class="ed_temp_contact"><td align="right">Phone Number:</td><td align="left"><input id="ed_phoneNumber" value=""></input></td><td align="right">Phone Type:</td><td align="left"><input id="ed_phoneType" value=""></input></td><td align="right">Contact Type:</td><td align="left"><input id="ed_contactType" value=""></input></td><td align="right">Email Id:</td><td align="left"><input id="ed_emailId" value=""></input></td></tr>';
				        		  extraHeight = extraHeight + 40;
				        	  } else {
					        	  for (x in tempContactsArray){
					        		  tempDiv = tempDiv + '<tr id="ed_rowList'+x+'" class="ed_temp_contact"><input id="ed_contactId" type="hidden" value="'+tempContactsArray[x].contactId+'"><td align="right">Phone Number:</td><td align="left"><input id="ed_phoneNumber" value="'+tempContactsArray[x].phoneNumber+'"></input></td><td align="right">Phone Type:</td><td align="left"><input id="ed_phoneType" value="'+tempContactsArray[x].phoneType+'"></input></td><td align="right">Contact Type:</td><td align="left"><input id="ed_contactType" value="'+tempContactsArray[x].contactType+'"></input></td><td align="right">Email Id:</td><td align="left"><input id="ed_emailId" value="'+tempContactsArray[x].emailId+'"></input></td></tr>';
					        		  extraHeight = extraHeight + 40;
					        	  }
				        	  }
				        	  
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
	};
	/* Fetch all Customer - fetch button event - End */

	/* Edit a Customer - ed_save button event - Start */
	var editSave = function(){
		
		$("#ed_save").attr("disabled","disabled");
		$("#waitText4").text('Please wait ... ');
		$("#ed_save").css('cursor','progress');
		
		var arr = [];
		var contactRows = $(".ed_temp_contact").length;
		for (var count=0; count<contactRows; count++){
			var contact = {
					contactId : $("#ed_rowList"+count+" [id~='ed_contactId']").val(),
					phoneNumber: $("#ed_rowList"+count+" [id~='ed_phoneNumber']").val(),
					phoneType: $("#ed_rowList"+count+" [id~='ed_phoneType']").val(),
					contactType: $("#ed_rowList"+count+" [id~='ed_contactType']").val(),
					emailId : $("#ed_rowList"+count+" [id~='ed_emailId']").val(),
			};
			arr.push(contact);
		}

		var formElements = new Object({
			customerId : $("#ed_customerId").val(),
			customerName: $("#ed_name").val(), 
			memberSince: new Date($("#ed_memberSince").val()),
			balance: parseFloat($("#ed_balance").val()),
			customerAddress : {
				addressId : $("#ed_addressId").val(),
				houseNumber: $("#ed_houseNumber").val(),
				street: $("#ed_street").val(),
				city: $("#ed_city").val(),
				state: $("#ed_state").val(),
				country: $("#ed_country").val()
			},
			defaultCard: {
				creditCardId : $("#ed_creditCardId").val(),
				cardNumber : $("#ed_cardNum").val(),
				securityCode : $("#ed_securityCd").val(),
				expDate : new Date($("#ed_expDate").val()),
				cardType : $("#ed_cardType").val()
			},
			contacts : arr
		});

		$.ajax({
			type: 'POST',
			url: contextPath+'/customer/update.do',
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
				url: contextPath+'/customer/remove.do?customerId='+customerId,
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
		if (!$("#customerAddForm").validate().form()){
			return false;
		}
		
		$("#saveButton").attr("disabled","disabled");
		$("#waitText2").text('Please wait ... ');
		$("#saveButton").css('cursor','progress');

		var arr = [];

		for (var count=0; count<=rowCount; count++){
			var contact = {
					phoneNumber: $("#rowList"+count+" [name~='phoneNumber']").val(),
					phoneType: $("#rowList"+count+" [name~='phoneType']").val(),
					contactType: $("#rowList"+count+" [name~='contactType']").val(),
					emailId : $("#rowList"+count+" [name~='emailId']").val(),
			};
			arr.push(contact);
		}

		var formElements = new Object({
			customerId : 1,
			customerName: $("#custName").val(), 
			memberSince: new Date($("#custMemberSince").val()),
			balance: parseFloat($("#custBalance").val()),
			customerAddress : {
				houseNumber: $("#addrHouseNumber").val(),
				street: $("#addrHouseStreet").val(),
				city: $("#addrCity").val(),
				state: $("#addrState").val(),
				country: $("#addrCountry").val()
			},
			defaultCard: {
				cardNumber : $("#cardNumber").val(),
				securityCode : $("#cardSecurityCode").val(),
				expDate : new Date($("#cardExpDate").val()),
				cardType : $("#cardType").val()
			},
			contacts : arr
		});

		$.ajax({
			type: 'POST',
			url: contextPath+'/customer/add.do',
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(formElements),
			success : function(data, textStatus, jqXHR) {
				$("#customerAddForm")[0].reset();
				
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
	/* Add Customer - submit button event - End */
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
