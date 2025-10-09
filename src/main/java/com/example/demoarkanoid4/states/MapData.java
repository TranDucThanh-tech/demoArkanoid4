package com.example.demoarkanoid4.states;

import com.example.demoarkanoid4.core.Wall;
import com.example.demoarkanoid4.core.brick.Brick;

import java.util.List;

public record MapData(List<Brick> bricks, List<Wall> walls) {}