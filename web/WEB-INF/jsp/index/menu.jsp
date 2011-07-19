<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                <div id="menu">
                    <ul>
                        <li>
                            <a href="<c:url value="/filial" />">Filiais</a>
                        </li>
                        <li>
                            <a href="<c:url value="/setor" />">Setores</a>
                        </li>
                        <li>
                            <a href="<c:url value="/funcionario" />">Funcion�rios</a>
                        </li>
                        <li>
                            <a href="<c:url value="/doador" />">Doadores</a>
                        </li>
                        <li>
                            <a href="<c:url value="/campanha" />">Campanhas</a>
                        </li>
                        <li>
                            <a href="#">Finan�as</a>
                            <div>
                                <ul>
                                    <li><a href="<c:url value="/doacao" />">Doa��es</a></li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <a href="#">Relat�rios</a>
                            <div>
                                <ul>
                                    <li><a href="#">Link 1</a></li>
                                    <li><a href="#">Link 1</a></li>
                                    <li><a href="#">Link 1</a></li>
                                    <li><a href="#">Link 1</a></li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <a href="<c:url value="/mensagem" />">Newsletter e SMS</a>
                        </li>
                    </ul>
                </div>