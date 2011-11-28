<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                <div id="menu">
                    <ul>
                        <li>
                            <a href="${linkTo[IndexController].index}">Início</a>
                        </li>
                        <li>
                            <a href="${linkTo[FilialController].list}">Filiais</a>
                        </li>
                        <li>
                            <a href="${linkTo[SetorController].list}">Setores</a>
                        </li>
                        <li>
                            <a href="${linkTo[FuncionarioController].list}">Funcionários</a>
                        </li>
                        <li>
                            <a href="${linkTo[DoadorController].list}">Doadores</a>
                        </li>
                        <li>
                            <a href="${linkTo[CampanhaController].list}">Campanhas</a>
                        </li>
                        <li>
                            <a href="#">Finanças</a>
                            <div>
                                <ul>
                                    <li><a href="${linkTo[DoacaoController].list}">Doações</a></li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <a href="#">Relatórios</a>
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
                            <a href="${linkTo[MensagemController].list}">Newsletter e SMS</a>
                        </li>
                    </ul>
                </div>