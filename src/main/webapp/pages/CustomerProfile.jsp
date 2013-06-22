<!DOCTYPE html>
<html lang="en-US">
<head>
<meta charset="UTF-8">

<title>Customer Profile</title>
<link rel="stylesheet" href="resources/custom.css" media="all"/>
<link rel="stylesheet" href="resources/jquery/css/jquery-ui-1.10.2.smoothness.min.css" media="all"/>
<link rel="stylesheet" href="resources/jqwidgets/css/jqx.base.css" type="text/css" />

<script src="resources/jquery/js/jquery-1.9.1.min.js"></script>
<script src="resources/jquery/js/jquery-ui-1.10.2.smoothness.min.js"></script>
<script src="resources/jquery/js/jquery-validate.min.js"></script>

<script src="resources/jqwidgets/js/jqxcore.js"></script>
<script src="resources/jqwidgets/js/jqxdata.js"></script> 
<script src="resources/jqwidgets/js/jqxbuttons.js"></script>
<script src="resources/jqwidgets/js/jqxscrollbar.js"></script>
<script src="resources/jqwidgets/js/jqxmenu.js"></script>
<script src="resources/jqwidgets/js/jqxgrid.js"></script>
<script src="resources/jqwidgets/js/jqxgrid.selection.js"></script>
<script src="resources/jqwidgets/js/jqxtabs.js"></script>
<script src="resources/jqwidgets/js/jqxgrid.selection.js"></script>
<script src="resources/jqwidgets/js/jqxgrid.sort.js"></script> 
<script src="resources/jqwidgets/js/jqxwindow.js"></script>
<script src="resources/jqwidgets/js/jqxinput.js"></script>
<script src="resources/jqwidgets/js/jqxgrid.columnsresize.js"></script>
<script src="resources/jqwidgets/js/jqxgrid.pager.js"></script>
<script src="resources/jqwidgets/js/jqxlistbox.js"></script>
<script src="resources/jqwidgets/js/jqxdropdownlist.js"></script>

<script src="resources/jqwidgets/js/gettheme.js"></script>

<script src="resources/CustomerProfile.js"></script>

<script>
	rowCount=0;
	contextPath = '<%= request.getContextPath() %>';	
</script>
</head>

<body>
<div id="accordionAll">
	<h3>Show all Customer</h3>
	<div id="customerAll">
		<button id="fetch" style="margin-right: 5px; font-size: 13px;">FETCH ALL</button><span id="waitText1"></span><button id="deleterowbutton" style="margin-right: 5px; font-size: 13px;">DELETE SELECTED</button><span id="waitText3"></span>
		<br><br>
		<div id="searchDiv" style="border: 1px solid #A9A9A9; padding: 6px; border-radius: 4px;">
			<input type="text" id="searchCustomerName">&nbsp;&nbsp;<input type="text" id="searchHouseNo">&nbsp;&nbsp;<input type="text" id="searchStreet">&nbsp;&nbsp;<button id="searchButton" style="margin-right: 5px; font-size: 13px;">SEARCH</button>
		</div>
		<br>
		<div id='jqxWidget' style="float: left; width: 100%">
			<div id="jqxgrid"></div>
		</div>	
	</div>
	
	<h3>Add a Customer</h3>
	<form id="customerAddForm" action="#" method="POST">
	<div id="customerAdd">
		<div id="accordionAdd">
			<h3>Customer Details</h3>
			<div id="customerMain">
				<table>
					<tr><td><label for="custName">Customer Name : </label></td><td><input type="text" id="custName"/></td></tr>
					<tr><td><label>Member since : </label></td><td><input type="text" id="custMemberSince" name="memberSince" /></td></tr>
					<tr><td><label>Balance : </label></td><td><input type="text" id="custBalance" name="balance" /></td></tr>
				</table>
				<br>
			</div>
		
			<h3>Address</h3>
			<div id="addressDetails">
				<table>
					<tr><td><label>House Number : </label></td><td><input type="text" id="addrHouseNumber" name="houseNumber"/></td></tr>
					<tr><td><label>Street : </label></td><td><input type="text" id="addrHouseStreet" name="street"/></td></tr>
					<tr><td><label>City : </label></td><td><input type="text" id="addrCity" name="city"/></td></tr>
					<tr><td><label>State : </label></td><td><input type="text" id="addrState" name="state"/></td></tr>
					<tr><td><label>Country : </label></td><td><input type="text" id="addrCountry" name="country"/></td></tr>
				</table>
			</div>
		
			<h3>Credit Card</h3>
			<div id="creditCardDetails">
				<table>
					<tr><td><label>Card Number : </label></td><td><input type="text" id="cardNumber" name="cardNumber"/></td></tr>
					<tr><td><label>Security code : </label></td><td><input type="text" id="cardSecurityCode" name="securityCode"/></td></tr>
					<tr><td><label>Exp. Date : </label></td><td><input type="text" id="cardExpDate" name="expDate"/></td></tr>
					<tr><td><label>Card Type : </label></td><td><input type="text" id="cardType" name="cardType"/></td></tr>
				</table>
			</div>
		
			<h3>Contact Details</h3>
			<div id="contactDetails" style="display: block; height: 100px !important; overflow: visible;">
				<p id="addRow" style="cursor: pointer;">Add More contacts<img src="./resources/image/add.png" alt="Add"></p>
				<table id="contactTable">
					<tr id="rowList0">
						<!-- <td><img id="addRow" class="addRowClass" src="./resources/add.png" alt="Add" style="cursor: pointer;"></td> -->
						<td><label>Phone Number : </label><input type="text" id="contactPhoneNumber" name="phoneNumber"/></td>
						<td><label>Phone Type : </label><input type="text" id="contactPhoneType" name="phoneType"/></td> 
					 	<td><label>Contact Type : </label><input type="text" id="contactType" name="contactType"/></td>
					 	<td><label>Email Id : </label><input type="text" id="contactEmailId" name="emailId"/></td>
					</tr>
				</table>
			</div>
		</div>
		<br>
		<button id="saveButton" style="margin-right: 5px; font-size: 13px;">SAVE</button><span id="waitText2"></span><button id="resetAdd" type="reset" style="font-size: 13px;">CLEAR</button>
	</div>
	</form>
</div>

<div id="popupWindow" style="display: none;"> 
     <div>Edit</div>
     <input id="ed_customerId" type="hidden" />
     <input id="ed_addressId" type="hidden" />
     <input id="ed_creditCardId" type="hidden" />
     <div style="overflow: hidden;">
         <table id="editTable">
             <tr>
                 <th colspan="5" align="left">Customer Details</th>
             </tr>
             <tr>
                 <td align="right">Name:</td>
                 <td align="left"><input id="ed_name"/></td>
                 <td align="right">Member Since:</td>
                 <td align="left"><input id="ed_memberSince" /></td>
                 <td align="right">Balance:</td>
                 <td align="left"><input id="ed_balance" /></td>
             </tr>
             <tr>
                 <th colspan="5" align="left">Address</th>
             </tr>
             <tr>
                 <td align="right">House Number:</td>
                 <td align="left"><input id="ed_houseNumber"></input></td>
                 <td align="right">Street:</td>
                 <td align="left"><input id="ed_street"></input></td>
                 <td align="right">City:</td>
                 <td align="left"><input id="ed_city"></input></td>
                 <td align="right">State:</td>
                 <td align="left"><input id="ed_state"></input></td>
             </tr>
             <tr>
                 <td align="right">Country:</td>
                 <td align="left"><input id="ed_country"></input></td>
             </tr>
             <tr>
                 <th colspan="5" align="left">Credit Card</th>
             </tr>
             <tr>
                 <td align="right">Card Number:</td>
                 <td align="left"><input id="ed_cardNum"></input></td>
                 <td align="right">Security Code:</td>
                 <td align="left"><input id="ed_securityCd"></input></td>
                 <td align="right">Exp. Date:</td>
                 <td align="left"><input id="ed_expDate"></input></td>
                 <td align="right">Card Type:</td>
                 <td align="left"><input id="ed_cardType"></input></td>
             </tr>
             <tr>
                 <th colspan="5" align="left">Contact Details</th>
             </tr>
         </table>
     </div>
</div>

</body>
</HTML>
<script>
$(function(){
	$("#customerMain").css( "height", "100%" );
	$("#addressDetails").css( "height", "100%" );
	$("#creditCardDetails").css( "height", "100%" );
	$("#contactDetails").css( "height", "100%" );
});
</script>