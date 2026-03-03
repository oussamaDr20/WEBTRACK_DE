<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ page import="entities.Dossier" %>
        <%@ page import="entities.Responsable" %>
        <%@ page import="entities.Tache" %>
    
    <%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="web.TrackingServlet"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Admin</title>

  <!-- Favicons -->
  <link href="img/favicon.png" rel="icon">
  <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Bootstrap core CSS -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!--external css-->
  <link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles for this template -->
  <link href="css/style1.css" rel="stylesheet">
  <link href="css/style-responsive.css" rel="stylesheet">
  
</head>

<body>
  <section id="container">
    <!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
    <!--header start-->
    <header class="header black-bg">
      <div class="sidebar-toggle-box">
        <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
      </div>
      <!--logo start-->
      <a href="http://localhost:8080/Tracking/Admin" class="logo"><b>WEB<span>TRACK</span></b></a>
      <!--logo end-->
      <div class="nav notify-row" id="top_menu">
        <!--  notification start -->
        
          </li> 
        </ul> 
      </div>
      <div class="top-menu">
        <ul class="nav pull-right top-menu">
          <li>
            <a class="logout" href="http://localhost:8080/Tracking/deconnexion">Abmelden</a>
          </li>
        </ul>
      </div>
    </header>
    
    <aside>
      <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
          <p class="centered">
            <a ><img src="img/me.jpg" class="img-circle" width="100"></a>
          </p>
          <h5 class="centered">Aymen Benjbara</h5>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-desktop"></i>
              <span>Gesamtansicht</span>
              </a>
            <ul class="sub">
              <li>
                <a href="http://localhost:8080/Tracking/AdminDoc">Liste der Ordner</a>
              </li>
              <li>
                <a href="http://localhost:8080/Tracking/AdminRespo">Liste der Verantwortlichen</a>
              </li>
              <li>
                <a href="http://localhost:8080/Tracking/AdminClient">Liste der Kunden</a>
              </li> 
            </ul>
          </li> 
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-desktop"></i>
              <span>Handlungen</span>
              </a>
            <ul class="sub">
              <li>
                <a href="http://localhost:8080/Tracking/ajoutDoc">Einen Ordner hinzufügen</a>
              </li>
              <li>
                <a href="http://localhost:8080/Tracking/ajoutRespo">Einen Manager hinzufügen</a>
              </li>
              <li>
                <a href="http://localhost:8080/Tracking/ajoutTache">Verwaltung der Aufgaben</a>
              </li> 
            </ul>
          </li> 
           
        </ul>
        <!-- sidebar menu end-->
      </div>
    </aside>
    
    
    <section id="main-content">
      <section class="wrapper">
        </div> 
         
        <!-- /row -->
        <div class="row mt">
          <div class="col-md-12">
            <div class="content-panel">
              <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i> Kundenliste</h4>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bookmark"></i>Kundennummer</th>
                    <th class="fa fa-edit"> </i>Name</th>
                    <th><i class="fa fa-edit"></i>Email</th>
                    <th><i class=" fa fa-edit"></i> Passwort</th>
                    <th class="fa fa-edit"> </i>Adresse</th>
                    <th><i class="fa fa-edit"></i>Telefonnummer</th>
                    <th><i class=" fa fa-edit"></i>Zustand</th>
                      <th></th>
                  </tr>
                </thead>
                <tbody>
                
                <c:forEach items="${ client}" var="client">
                  <tr>
                    <td>${ client.id_client}</td>
                    <td>${ client.nom}</td>
                     <td>${ client.email}</td>
                    <td>${ client.password}</td> 
                     <td>${ client.adresse}</td>
                     <td>${ client.tel}</td>
                    <td>
                    <c:if test="${client.premium == true}">
		<span class="label label-warning label-mini">PREMIUM</span>
			  </c:if>
			   <c:if test="${client.premium == false}">
		<span class="label label-success label-mini">Standard</span>
			  </c:if>
			  </td> 
                     <td>
                     <a href="http://localhost:8080/Tracking/editClient?id_r=${ client.id_client}"> <button class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></button></a>
                     <a href="http://localhost:8080/Tracking/deleteClient?id_r=${ client.id_client}"  onclick="return confirm('Voulez vous vraiment supprimer ce client?')"><button class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></button></a>
                      </td>
                 </tr>
                  </c:forEach>
                   
                </tbody>
              </table>
            </div>
            <!-- /content-panel -->
          </div>
          <!-- /col-md-12 -->
        </div>
      </section>
    </section>
    <!-- /MAIN CONTENT -->
    <!--main content end-->
    <!--footer start-->
     
    <!--footer end-->
  </section>
  <!-- js placed at the end of the document so the pages load faster -->
  <script src="lib/jquery/jquery.min.js"></script>
  <script src="lib/bootstrap/js/bootstrap.min.js"></script>
  <script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
  <script src="lib/jquery.scrollTo.min.js"></script>
  <script src="lib/jquery.nicescroll.js" type="text/javascript"></script>
  <!--common script for all pages-->
  <script src="lib/common-scripts.js"></script>
  <!--script for this page-->
  
</body>

</html>
