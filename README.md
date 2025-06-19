# AdoteMeApp 🐾

<div align="center">
  <img src="https://github.com/user-attachments/assets/933bfeb8-e097-4122-bf9c-03e5ea7db57a" alt="logo_adotme_nobg">
</div>

AdoteMeApp é um aplicativo mobile desenvolvido em Kotlin com Jetpack Compose para facilitar o processo de adoção de animais. Usuários podem se cadastrar, preencher formulários, visualizar animais disponíveis, favoritar pets, realizar solicitações de adoção e acompanhar o status das suas aplicações. ONGs podem cadastrar seus animais e receber solicitações diretamente pelo sistema.

Este projeto tem como objetivo promover a adoção responsável de animais, conectando pessoas e ONGs de forma simples e eficiente.

<div align="center">
  <img src="https://github.com/user-attachments/assets/cf30c57b-1f40-4523-a6c3-31169a474657" width="800" alt="adoteme_app">
</div>

## Funcionalidades ✨

- Cadastro e autenticação de usuários
- Preenchimento de formulário de perfil e de adoção
- Listagem de animais disponíveis para adoção, com filtros por categoria, espécie, porte e sexo
- Visualização de detalhes dos animais, incluindo fotos e personalidade
- Favoritar/desfavoritar animais
- Solicitação de adoção para ONGs cadastradas
- Acompanhamento do status das solicitações de adoção
- Listagem de ONGs e informações bancárias para doações
- Upload de foto de perfil do usuário

## Tecnologias Utilizadas 🛠

- **Kotlin** & **Jetpack Compose**: Interface moderna e reativa para Android
- **Koin**: Injeção de dependências
- **Retrofit** & **OkHttp**: Comunicação com APIs REST
- **Gson**: Serialização e desserialização de dados JSON
- **Coil**: Carregamento de imagens

## Estrutura do Projeto 🗂

- `app/src/main/java/com/example/adoteme_app/`
  - `presentation/`: Telas, componentes de UI e ViewModels
  - `domain/`: Modelos de dados e casos de uso
  - `data/`: Repositórios e serviços de rede (APIs)
  - `application/di/`: Configuração de injeção de dependências (Koin)

## Como rodar o projeto ▶

1. Clone este repositório.
2. Abra o projeto no Android Studio.
3. Configure um dispositivo/emulador Android.
4. Execute o projeto (`Run > Run 'app'`).
