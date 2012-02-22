<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                <div id="menu">
                    <ul>
                        <li>
                            <a href="${linkTo[IndexController].index}">In�cio</a>
                        </li>
                        <li>
                            <a href="${linkTo[DoadorController].list}">Doadores</a>
                        </li>
                        <li>
                            <a href="${linkTo[DoacaoController].list}">Doa��es</a>
                        </li>
                        <li>
                            <a href="${linkTo[CampanhaController].list}">Campanhas</a>
                        </li>
                        <li>
                            <a href="#">Relat�rios</a>
                            <div>
                                <ul>
                                    <li><a href="${linkTo[ReportController].donationsByPeriod}">Doa��es por per�odo</a></li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <a href="${linkTo[MensagemController].list}">Newsletter</a>
                        </li>
                    </ul>
                </div>