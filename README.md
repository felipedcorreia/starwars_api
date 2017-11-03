TESTE PARA
DESENVOLVEDOR ANDROID

Nós aqui da ACCERA estamos em busca de pessoas que tenham interesse, proatividade para aprender, e, principalmente,
queiram contribuir com a evolução e crescimento em conjunto com o time!
Pensando nisso abrimos mais oportunidades para compor nosso time de desenvolvimento que atua em nosso produto TRADE
FORCE, uma solução de Pesquisa e Auditoria em Pontos de Venda, extremamente aderente aos negócios dos nossos clientes,
agregando valor à operação e extraindo todo o potencial estratégico em campo.
Elaboramos um teste que irá avaliar seus conhecimentos técnicos em desenvolvimento utilizando Java para a plataforma
Android.

OBJETIVO GERAL
Criação de um aplicativo Android nativo capaz de listar personagens dos filmes Star Wars com informações obtidas a partir da
leitura de um QRCode, as informações lidas deverão ser armazenadas localmente, o usuário poderá listá-las e selecionar itens
para ver detalhes.

FUNCIONALIDADES OBRIGÁTÓRIAS
1) Captura de QR Code e armazenamento do código lido em base de dados local.
a) O QR Code deverá conter uma URL de personagem do Star Wars disponibilizada pela Star Wars Api. Exemplo:
http://swapi.co/api/people/1/
b) Dica: QR Codes podem ser gerados em qualquer site para geração deste tipo de código.
2) Exibir ao usuário uma listagem com os personagens já lidos e armazenados localmente. Os itens da lista devem conter o
nome do usuário e a URL lida no QR Code.
3) Possibilitar ao usuário tocar em um item da lista para exibir os detalhes do personagem.
a) Os detalhes devem ser obtidos da Star Wars Api.
b) Junto com os detalhes do personagem deve ser exibida uma lista com os nomes dos filmes nos quais ele apareceu,
também disponíveis na Star Wars Api.

FUNCIONALIDADES DESEJÁVEIS
1) Ao capturar o QR Code capturar e armazenar em base local também as informações de geolocalização do usuário no
momento da captura.
2) Ao exibir os detalhes do personagem do usuário exibir:
a) Data da captura e dados de geolocalização da captura (onde foi capturado).
b) Na listagem de filmes do personagem exibir uma imagem de pôster para cada um dos filmes. Possibilitar ao usuário
tocar no filme e redirecioná-lo para o site do filme.
i) O pôster e o website de cada filme podem ser obtidos utilizando a The Movie Db Api.
3) Cache das informações:
a) Após cada requisição fazer cache local dos dados do personagem e informações dos filmes (imagem, URL do filme
etc).
b) Possibilitar ao usuário visualizar as informações mesmo offline.

PREMISSAS
Você tem liberdade para decidir:
1) Quais bibliotecas e ferramentas irá utilizar.
2) A melhor arquitetura para seu app.
3) Como serão as UIs.

O QUE IREMOS AVALIAR
1) Qualidade do código.
2) Bom uso de OOP, OOD e princípios SOLID.
3) Uso de padrões de projeto.
4) Utilização de Testes Automatizados.
5) Qualidade das Interfaces do Usuário e fluxo de telas.
6) Qualidade do repositório Git e Commits realizados.

ENTREGA
Deverá ser feita com a publicação do código em um repositório público no Github e uma versão release publicada no próprio
Github.
Lembre-se de incluir um README em seu projeto explicando algumas informações sobre seu projeto como o que ele faz,
ferramentas e frameworks utilizados e qualquer detalhe que você ache interessante.
