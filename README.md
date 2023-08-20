# testeTGID

- Organizado infraestrutura do software em camadas (controller, business e model), seguindo os conceitos da orientação em objeto, para que uma possível manuntenção no projeto fosse mais fácil de ser feita, pois assim faz com que o código fique mais legível, melhorando a compreensão do mesmo.

- Refatorado o sistema de Menus, onde o número de cada opção era o ID do produto, foi modificado para que, o primeiro produto que fosse mostrado na tela fosse a opção um, o segundo a opção dois e assim por diante. Para isso, foi utilizado um array que vinculasse o número da opção com o ID do produto, isso melhora a usuabilidade do software e faz com que não precise de manutenções no código futuramente.

- Foi analisado na documentação que as vendas produtos eram realizadas de acordo com a quantidade no estoque, porém, o programa não atualizava a quantidade quando um item era adicionado no carrinho. Com isso, foi adicionado um código de verificação para implementar a regra de negócio, caso o item esteja esgotado, é informado uma mensagem para o usuário.

- Quando o acesso para o software era negado, o projeto para de ser executado, foi adicionado a função para retornar e digitar novamente.

- Adicionado validação nos menus iniciais

- Corrigido erros de português e espaçamento nos textos
