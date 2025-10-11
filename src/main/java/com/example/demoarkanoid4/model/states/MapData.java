package com.example.demoarkanoid4.model.states;

import com.example.demoarkanoid4.model.core.Wall;
import com.example.demoarkanoid4.model.core.brick.Brick;

import java.util.List;

public record MapData(List<Brick> bricks, List<Wall> walls) {}