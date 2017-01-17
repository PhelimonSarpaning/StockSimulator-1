<%-- 
    Document   : index
    Created on : 7 Sep, 2015, 8:47:17 PM
    Author     : Aniket
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="bootstrap.min.css" rel="stylesheet">
	<script src="jquery-2.0.3.js"></script>
    <script src="bootstrap.min.js"></script>
        <style>
            body{
                background-color: #00CFBE;
            }
            .loginform{
                margin-top: 60px;
            }
        </style>
    </head>
    <body>
        <div class="container">
	               <div class="row">
				   
				   <div id="top-head" class="navbar navbar-default" role="navigation">
			     
              <!--first half--> 
			 <div class="col-sm-4">
			  <div class="navbar-collapse collapse">	
			  <ul class="nav navbar-nav">
			    <li><a href="#">About us</a></li>
			    <li><a href="#">Contact us</a></li>
			  
			  </ul>
                         </div>
			  </div>
			  
			   <!--2nd half--> 
			  <div class="col-sm-4">
			 
				 <div class="navbar-header">
				<!--span class="navbar-brand">&nbsp;&nbsp;<span class="glyphicon glyphicon-resize-full"></span><span class="glyphicon glyphicon-stats"--></span><font face="Agency FB" style="bold" size="5">&nbsp;&nbsp;&nbsp;&nbsp;BooT-StockS</font></span>
				
				<button type="button" class="navbar-toggle" data-toggle="collapse" 
				data-target=".navbar-collapse">
				 <span class="icon icon-bar"></span>
				 <span class="icon icon-bar"></span>
				 <span class="icon icon-bar"></span>
				</button>
				</div>
                              
                               </div>
                    
					 <div class="col-sm-4">
                  <div class="navbar-collapse collapse">				 
				 <ul class="nav navbar-nav">
				                <li><a href="#">Feedback</a></li>
								<li><a href="#">Open Source</a></li>
								
				 </ul>
			  </div>
			  
          </div>
                           
     </div>			   
						   
	 </div>
        </div>
        
    
        
        
    <center>
        <form class="loginform" action="loginaction" method="post">
            <input type="text" name="username" placeholder="Username" /><br /><br />
             <input type="password" name="password" placeholder="Password" /><br /><br />
             <input type="submit" value="Log in" />
        </form>
    </center>
    </body>
</html>
