![build workflow](https://github.com/Zigu/graphql-poc/actions/workflows/gradle.yml/badge.svg)

# POC project for Spring GraphQL

This project contains test classes to get familiar with Spring GraphQL.

One interesting point was to deal with subclasses in GraphQL as well as in MapStruct.

## No code generator included

I was testing [server code generation](https://github.com/graphql-java-generator/graphql-gradle-plugin-project) but it did not fit my expectations 
(i.e. I only wanted to create controllers and DTOs but not the application main class).
