package com.pinheirosapplications.marvelapi;

import com.pinheirosapplications.marvelapi.models.Hero;

import java.util.List;

public class Sessao {
    private static List<Hero> heroList;

    public static List<Hero> getHeroList() {
        return heroList;
    }

    public static void setHeroList(List<Hero> heroList) {
        Sessao.heroList = heroList;
    }
}
