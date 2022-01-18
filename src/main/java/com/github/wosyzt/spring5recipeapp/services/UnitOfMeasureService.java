package com.github.wosyzt.spring5recipeapp.services;

import com.github.wosyzt.spring5recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
