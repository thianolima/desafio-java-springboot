package br.com.desafiojavaspringboot.templates;

import com.github.javafaker.Faker;

import java.util.Locale;

public abstract class BaseTemplate {

    protected final Faker faker = new Faker(new Locale("pt-BR"));
}
