<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.uniroma2.ispw.bean.*" %>
<%@ page import="it.uniroma2.ispw.controller.*" %>
<%@ page import="it.uniroma2.ispw.model.*" %>
<%@ page import="it.uniroma2.ispw.session.*" %>
<%@ page import="java.util.*" %>
<%@ page errorPage = "errorpage.jsp" %>
  
<jsp:useBean id="ricercab" scope="session" class="it.uniroma2.ispw.bean.RicercaBean"/>
<jsp:setProperty name="ricercab" property="*"/>

<jsp:useBean id="acquistob" scope="session" class="it.uniroma2.ispw.bean.AcquistoBean"/>
<jsp:setProperty name="acquistob" property="*"/>

<jsp:useBean id="pagamentob" scope="session" class="it.uniroma2.ispw.bean.PagamentoBean"/>
<jsp:setProperty name="pagamentob" property="*"/>
    
    
 <%
 
 UtenteSessione us = (UtenteSessione) session.getAttribute("utente"); 
 CarrelloBean carb = (CarrelloBean) session.getAttribute("carrello");

 acquistob.setProdotti(carb.getListaProdottiBean());
 

%>



<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shopping-Express</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

    <!-- Theme CSS -->
    <link href="css/agency.min.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body id="page-top" class="index">

    <!-- Navigation -->
    <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">Shopping Express</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="index.jsp">Cerca prodotto</a>
                    </li>
                  
                    
                    <%if (us==null){ %>
                    
                    <li>
                        <a class="forget" data-toggle="modal" data-target=".forget-modal-login" href="#modalLogin">Login</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="registrazione.jsp">Registrazione</a>
                    </li>
                    
                    <%}else if (us.getType()==2){ %>
                     <li>
                        <a class="forget" data-toggle="modal" data-target=".forget-modal-logout" href="#modalLogout" >Logout</a>
                    </li>
                     <li>
                        <a class="page-scroll" href="profilo.jsp">Profilo</a>
                    </li>
                      <li>
                        <a class="page-scroll" href="listaAcquisti.jsp">Tuoi acquisti</a>
                    </li>
                     <li>
                        <a class="page-scroll" href="carrello.jsp">Carrello</a>
                    </li>
                   
                    <%}else if (us.getType()==1) { %>
                    <li>
                        <a class="forget" data-toggle="modal" data-target=".forget-modal-logout" href="#modalLogout" >Logout</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="profilo.jsp">Profilo</a>
                    </li>
                     <li>
                        <a class="page-scroll" href="annunci.jsp">Tuoi annunci</a>
                    </li>
                     <li>
                        <a class="page-scroll" href="prova.jsp">Tue Vendite</a>
                    </li>
                    
                    
                    <%}%>
                    <li>
                        <a class="page-scroll" href="#team"></a>
                    </li>
                
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

	
<!-- popup logout -->
	<div id="modalLogout" class="modal fade forget-modal-logout" tabindex="-1" role="dialog" aria-labelledby="myLoginModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">x</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">Logout</h4>
					</div>	
					<div class="modal-body">
						<p>Sicuro di abbandonare la sessione?</p>					
					</div>
					<div class="modal-footer">	
					
					<form action="index.jsp" method="post">		
						<input class="btn btn-custom" type="submit" id="btn-logout" name="logout" value="Abbandona">
					</form>
					</div>
				</div>
			</div>
	</div>	

    <!-- Header -->
    <header>
        <div class="container">
            <div class="intro-text">
                
                <%if (us==null){ %>
                	<div class="intro-lead-in">Benvenuto nel nostro sito di e-commerce!</div>
                	<div class="intro-heading">It's Nice To Meet You</div>
                <%}else if ((us.getType()==1)||(us.getType()==2)){ %>
                
                	<div class="intro-lead-in">Bentornato nel nostro sito di e-commerce, <%= us.getUserid() %>!</div>
                	<div class="intro-heading">It's Nice To Meet You</div>
                
                
                <%} %>
            </div>
               
        </div>

    
       
    </header>
    
<div id="page-wrapper">
	<div class="row">
    	<div class="col-lg-12">
<div class="panel panel-default">
	<div class="panel-heading">Acquisto</div>
	<div class="panel-body">
	<p>Per effettuare l'acquisto scegliere il metodo di pagamento preferito e completare i campi obligatori con i dati personali</p>
	</div>
	
	<table class="table">
	<thead>
		<tr>
			<th>#</th>
			<th>Nome prodotto</th>
			<th>Prezzo</th>
		</tr>
	</thead>	
		<tbody>
	<%
		int prezzoTot = 0;
		List<ProdottoBean> lpB = carb.getListaProdottiBean();
		ProdottoBean pB = null;
		if(lpB.size() != 0){
			for(int i=0; i<lpB.size(); i++){
				pB = lpB.get(i);
				prezzoTot += lpB.get(i).getPrice();
	%>
	
	
		<tr>
			<th scope="row"><%= i %></th>
			<td>$<%= pB.getNameProduct()%></td>
			<td>$<%= pB.getPrice()%></td>
		</tr>
	

	<% }} %>
		
		</tbody>
	</table>

	<div class="panel-footer">
		<h4>$<%= prezzoTot %></h4>
	</div>
</div>
</div>
</div>
</div>
    
    
 <div class="row">   
    <div class="col-lg-12">
    <form action="riassunto.jsp" method="post">
		<div class="panel panel-default">
			<div class="panel-heading">Pagamento con carta di credito<input type="checkbox" name="pagamentoCarta" value="pagamentoCarta"></div>
			<div class="panel-body">
				<label for="numeroCartaPag" class="sr-only">Numero carta</label>
				<input type="text" id="numeroCarta" name="numeroCarta" class="form-control" placeholder="Numero carta">
			</div>
			<div class="panel-body">
				<label for="scadenzaCartaPag" class="sr-only">Scadenza carta</label>
				<input type="text" id="scadenzaCarta" name="scadenzaCarta" class="form-control" placeholder="Scadenza carta">
			</div>
			<div class="panel-body">
				<label for="numeroDiSicurezzaPag" class="sr-only">Numero di sicurezza</label>
				<input type="text" id="numeroDiSicurezza" name="numeroDiSicurezza" class="form-control" placeholder="Numero di sicurezza">			
			</div>
		</div>
		
		
		<div class="panel panel-default">
			<div class="panel-heading">Pagamento con bonifico bancario<input type="checkbox" name="pagamentoBonifico" value="pagamentoBonifico"></div>
			<div class="panel-body">
				<label for="codiceIbanPag" class="sr-only">Codice IBAN</label>
				<input type="text" id="codiceIBAN" name="codiceIBAN" class="form-control" placeholder="Codice IBAN">
			</div>
			<div class="panel-body">
				<label for="causalePag" class="sr-only">Causale</label>
				<input type="text" id="causale" name="causale" class="form-control" placeholder="Causale">
			</div>
		</div>
		
		<div class="panel panel-default">
			<div class="panel-heading">Recapito</div>
			<div class="panel-body">
				<label for="recapito" class="sr-only">Recapito</label>
				<input type="text" id="recapito" name="recapito" class="form-control" placeholder="Recapito">
			</div>
		</div>
	
	
	
		<div class="panel panel-default">
			<div class="panel-heading">Tipo spedizione</div>
			<div class="panel-body">
				<div class="checkbox">
				<input type="radio" name="tipoSpedizione" value="Normale" id="check_value" class="text-primary">Spedizione normale <br>
				<input type="radio" name="tipoSpedizione" value="Rapida" id="check_value" class="text-primary">Spedizione rapida <br>
			</div>
			</div>
			
		</div>	
	
		<div class="panel panel-default">
			<div class="panel-heading">Concludi acquisto</div>
			<div class="panel-body">
				<input type="submit" class="btn-primary" id="inviaDati" name="inviaDati" value="Invia dati"> 
			</div>
		</div>
	</form>
    </div>
 </div>   
    
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <span class="copyright">Copyright &copy; Your Website 2016</span>
                </div>
                <div class="col-md-4">
                    <ul class="list-inline quicklinks">
                        <li><a href="#">Regolamento</a>
                        </li>
                        <li><a href="#">Contattaci</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>
    

    <!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="js/jqBootstrapValidation.js"></script>
    <script src="js/contact_me.js"></script>

    <!-- Theme JavaScript -->
    <script src="js/agency.min.js"></script>

</body>

</html>
