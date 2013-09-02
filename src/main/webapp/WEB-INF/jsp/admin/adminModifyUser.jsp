<%@ include file="../common/commonJs.jsp" %>

<form:form name="modifyuser" id="modifyuser" method="post" novalidate="novalidate" action="" ng-controller="constantsController">

	<div id="rightdata" ng-controller="modifyUserController">
	  <div id="topcontent" style="margin-bottom:2px;">
	     	<h5>{{adminmoduser}}</h5>
	         <div class="signout">
	             <h5>${EmpForm.firstName} ${EmpForm.lastName}</h5>
	            <input type = "hidden" name = "userName" value="${userName}" />
	            <input type = "hidden" name = "empId" value="${EmpForm.id}" />
	            <input type = "hidden" name = "selecteddata" value ={{mySelections}} />
	            <a href="/lms-app">Sign Out</a>
	          </div>
	          <c:if test="${SucessMessage != null}">
		        <div class="success" style="margin: -27px 5px 5px 250px; width:42%;">${SucessMessage}</div>
		      </c:if>
	          <div id="search" ng-model="ngsearch" ng-show="ngsearch">
	            <span class="rc">Email Address :</span> 
	            <input style="margin-left:17px" type="email" name="emailSearch" id="searchEmail" ng-model="ngsearchemail" ng-change="changeEmail()" width-reducer required/>
	            <input type="button" name="Search" value="Search" ng-click="[search=true,searchuser(modifyuser,$event)]"/>
	            <span class="error" style="width: 20%;margin-top:-5px; margin-left:120px;" ng-show="search && modifyuser.emailSearch.$error.required">Email Address is Required.</span> 
	            <span class="error" style="width: 20%;margin-top:-5px; margin-left:120px;" ng-show="search && modifyuser.emailSearch.$error.email">Email Address is invalid.</span>
	            <div class="error" ng-model="userexistence" ng-show= "userexistence" style="margin: 120px 150px 150px 350px; width:32%;">Email Address Does Not Exists.</div>
	         </div>
	  </div>
	  <div id="adminleftcontent" ng-model="showdiv" ng-show="showdiv">
	   <table>
	    <tbody>
	      <tr><td><span class="rc">First Name :</span></td>
	          <td><input type="text" name="firstname" ng-model="ngfirstname" maxlength="30" width-reducer required/>
	      </tr>
	      <tr>
    	     <td></td>
    	     <td> 
    	     </td>
	     </tr>
	      <tr><td><span class="rc">Last Name :</span></td>
	          <td><input type="text" name="lastname" ng-model="nglastname" maxlength="30" width-reducer required/>
	      </tr>
	      <tr>
    	     <td></td>
    	     <td>
    	     </td>
	     </tr>
	     <tr><td><span class="rc">Employee ID :</span></td>
	          <td><input type="text" name="empid" ng-model="ngempid" width-reducer required/>
	      </tr>
	      <tr>
    	     <td></td>
    	     <td>
    	     </td>
	     </tr>
	      <tr><td><span class="rc">Email Address :</span></td>
	          <td><input type="email" name="email" ng-model="ngemail" width-reducer required/>
	      </tr>
	      <tr>
    	     <td></td>
    	     <td>
	    	     <span class="error" style="width: 80%;margin-top:-5px; margin-bottom:4px;" ng-show="submitted && modifyuser.email.$error.email">Email Address is invalid.</span>
    	     </td>
	     </tr>
	      <tr><td><span class="rc">Contact :</span></td>
	          <td><input type="text" name="phone" ng-model="ngphone" maxlength="10" width-reducer required/>
	      </tr>
	      <tr>
    	     <td></td>
    	     <td>
    	     </td>
	     </tr>
	      <tr><td><span class="rc">Password :</span></td>
	          <td><input type="password" name="password" ng-model="ngpassword" maxlength="10" ng-minlength="7" width-reducer required/>
	      </tr>
	      <tr>
    	     <td></td>
    	     <td>
    	       <span class="error" style="width: 85%; margin-top:-5px; margin-bottom:4px;" ng-show="submitted && modifyuser.password.$error.minlength">{{newpasswordminlength}}</span>
    	     </td>
	     </tr>
       	 <tr><td><span class="rc">Secondary Email :</span></td>
	   			 <td><input type="email" name="secemail" ng-model="ngsecemail" width-reducer/>
		 </tr>
	     <tr>
	  	 <td></td>
 			<td>
 			  <span class="error" style="width: 80%;margin-top:-5px; margin-bottom:4px;" ng-show="submitted && modifyuser.secemail.$error.email">Email Address is invalid.</span>
 			</td>
		 </tr>
	     
	    </tbody>
	   </table>
	  </div>
	  <div id="adminmiddlecontent" ng-model="showdiv" ng-show="showdiv">
		   <table>
		   	 <tbody>
		   	         <tr>
				       	<td><span class="rc">Date Of Join :</span></td>
						<td>
							<div class="control-group input-append">
								<input name="dateofjoin" id="dateofjoin" style="background-color: #FFFFFF; width:115px" class="input-small" type="text" ng-model="dateofjoin" 
								       data-date-format="dd/mm/yyyy" data-date-today-Highlight='true' bs-datepicker readonly ng-change="olddate($event)" required/>
				            	<button type="button" class="btn" data-toggle="datepicker"><i class="icon-calendar"></i></button>
			               	 </div>
			            </td>
			          </tr>
			          <tr>
			    	     <td></td>
			    	     <td>
				    	    <span class="error" style="width: 105%; margin-top:-5px; margin-bottom:4px;" ng-model="dateishigher" ng-show="dateishigher">Date is Join is incorrect.</span> 
			    	     </td>
				     </tr>
		   	         <tr><td><span class="rc">Designation :</span></td>
	          			 <td><input type="text" name="designation" ng-model="ngdesignation" width-reducer required/>
	      			 </tr>
	                 <tr>
    	     			<td></td>
    	     			<td>
    	     			</td>
	     			 </tr>
				   	 <tr>
					   <td><span class="rc">Department :</span></td>
				       <td><select name="leaveType" width-reducer>
			      				   <option value="admin">ADMIN</option>
			      				   <option value="admin">DEVELOPMENT</option>
							</select>
						</td>
				      </tr>
				     <tr><td><span class="rc">Level :</span></td>
				           <td><select name="level" width-reducer>
				      				   <option value="admin">Amin</option>
				      				   <option value="admin">Employee</option>
								</select>
							</td>
				      </tr>
				      <tr>
			    	     <td></td>
			    	     <td>
			    	     </td>
				     </tr>
				     <tr><td><span class="rc">Reporting To :</span></td>
				           <td><select name="level" width-reducer>
				      				   <option value="admin">Manager</option>
				      				   <option value="admin">Admin</option>
								</select>
							</td>
				      </tr>
				      <tr>
			    	     <td></td>
			    	     <td>
			    	     </td>
				     </tr>
				      <tr><td><span class="rc">Address :</span></td>
				          <td><textarea style="padding: 4px 6px; height:70px;" ng-model="ngaddress" name="address"  width-reducer required maxlength="100"></textarea></td>
				      </tr>
				      <tr>
			    	     <td></td>
			    	     <td> 
			    	     </td>
				     </tr>
		    </tbody>
		</table>
	</div>
	<div id="adminrightcontent" ng-model="showdiv" ng-show="showdiv">
		<table>
			<tbody>
				<tr><td><span class="rc">City :</span></td>
		            <td><input type="text" name="city" ng-model="ngcity" maxlength="20" width-reducer required/>
			    </tr>
			    <tr>
		    	    <td></td>
		    	    <td>
		    	    </td>
			    </tr>
			    <tr><td><span class="rc">State :</span></td>
			        <td><input type="text" name="state" ng-model="ngstate" width-reducer required/>
			    </tr>
			    <tr>
		    	    <td></td>
		    	    <td>
		    	    </td>
			    </tr>
			    <tr><td><span class="rc">Pincode :</span></td>
			        <td><input type="text" name="pincode" ng-model="ngpincode" maxlength="6" width-reducer required/>
			    </tr>
			    <tr>
		    	    <td></td>
		    	    <td>
		    	    </td>
			    </tr>
			    <tr>
		    	    <td></td>
		    	    <td>
		    	    <span class="error" style="width: 80%; margin-top:35px;  margin-bottom: -80px;" ng-model="submitted" ng-show="((submitted && modifyuser.firstname.$error.required) || (submitted && modifyuser.lastname.$error.required) ||
		    	                                                                                                            (submitted && modifyuser.email.$error.required) || (submitted && modifyuser.password.$error.required) ||
		    	                                                                                                            (submitted && modifyuser.dateofjoin.$error.required) || (submitted && modifyuser.designation.$error.required) ||
		    	                                                                                                            (submitted && modifyuser.address.$error.required) || (submitted && modifyuser.city.$error.required) ||
		    	                                                                                                            (submitted && modifyuser.state.$error.required) || (submitted && modifyuser.pincode.$error.required) ||
		    	                                                                                                            (submitted && modifyuser.level.$error.required) || (submitted && modifyuser.reportingto.$error.required) ||
		    	                                                                                                            (submitted && modifyuser.empid.$error.required) || (submitted && modifyuser.phone.$error.required))">All fields are mandatory.</span>
		    	    </td>
			    </tr>
			</tbody>
		</table>
		<div class="adminbuttonsalign">
	        <input type="submit" name="submit" value="Update" ng-click="[submitted=true,updateuser(modifyuser,$event)]"/> 
			<input type="button"  style="margin-left:10px;" name="back"   value="Back" ng-click="[takeuserback()]"/>
		</div>
	  </div>
	</div>
</form:form>