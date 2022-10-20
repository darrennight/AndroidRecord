package com.hao.androidrecord.activity.expandrv;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.expandrv.Artist;
import com.hao.androidrecord.activity.expandrv.Genre;
import com.hao.androidrecord.activity.expandrv.check.MultiCheckGenre;

import java.util.Arrays;
import java.util.List;

public class GenreDataFactory {

  public static List<Genre> makeGenres() {
    return Arrays.asList(makeRockGenre(),
        makeJazzGenre(),
        makeClassicGenre(),
        makeSalsaGenre(),
        makeBluegrassGenre());
  }

  public static List<MultiCheckGenre> makeMultiCheckGenres() {
    return Arrays.asList(makeMultiCheckRockGenre()
//            ,
//            makeMultiCheckJazzGenre(),
//            makeMultiCheckClassicGenre(),
//            makeMultiCheckSalsaGenre(),
//            makeMulitCheckBluegrassGenre()
    );
  }

  public static MultiCheckGenre makeMultiCheckRockGenre() {
    return new MultiCheckGenre("Rock", makeRockArtists(), R.drawable.ic_electric_guitar);
  }
  public static MultiCheckGenre makeMultiCheckJazzGenre() {
    return new MultiCheckGenre("Jazz", makeJazzArtists(), R.drawable.ic_saxaphone);
  }

  public static MultiCheckGenre makeMultiCheckClassicGenre() {
    return new MultiCheckGenre("Classic", makeClassicArtists(), R.drawable.ic_violin);
  }
  public static MultiCheckGenre makeMultiCheckSalsaGenre() {
    return new MultiCheckGenre("Salsa", makeSalsaArtists(), R.drawable.ic_maracas);
  }

  public static MultiCheckGenre makeMulitCheckBluegrassGenre() {
    return new MultiCheckGenre("Bluegrass", makeBluegrassArtists(), R.drawable.ic_banjo);
  }


  public static Genre makeRockGenre() {
    return new Genre("Rock", makeRockArtists(), R.drawable.ic_electric_guitar);
  }



  public static List<Artist> makeRockArtists() {
    Artist queen = new Artist("Queen", true);
    Artist styx = new Artist("Styx", false);
    Artist reoSpeedwagon = new Artist("REO Speedwagon", false);
    Artist boston = new Artist("Boston", true);
    Artist boston2 = new Artist("Boston2", true);
    Artist boston3 = new Artist("Bosto3", true);
    Artist boston4 = new Artist("Bosto4", true);
    Artist boston5 = new Artist("Bosto5", true);
    Artist boston6 = new Artist("Bosto6", true);
    Artist boston7 = new Artist("Bosto7", true);
    Artist boston8 = new Artist("Bosto8", true);
    Artist boston9 = new Artist("Bosto9", true);
    Artist boston10 = new Artist("Bosto10", true);
    Artist boston11 = new Artist("Boston11", true);
    Artist boston12 = new Artist("Boston11", true);
    Artist boston13 = new Artist("Boston11", true);
    Artist boston14 = new Artist("Boston11", true);
    Artist boston15 = new Artist("Boston11", true);
    Artist boston16 = new Artist("Boston11", true);
    Artist boston17 = new Artist("Boston11", true);
    Artist boston18 = new Artist("Boston11", true);

    return Arrays.asList(queen, styx, reoSpeedwagon, boston,boston2,boston3,
            boston4,boston5,boston6,boston7,boston8,boston9,boston10,boston11,
            boston12,boston13,boston14,boston15,boston16,boston17,boston18
            );
  }

  public static Genre makeJazzGenre() {
    return new Genre("Jazz", makeJazzArtists(), R.drawable.ic_saxaphone);
  }



  public static List<Artist> makeJazzArtists() {
    Artist milesDavis = new Artist("Miles Davis", true);
    Artist ellaFitzgerald = new Artist("Ella Fitzgerald", true);
    Artist billieHoliday = new Artist("Billie Holiday", false);

    return Arrays.asList(milesDavis, ellaFitzgerald, billieHoliday);
  }

  public static Genre makeClassicGenre() {
    return new Genre("Classic", makeClassicArtists(), R.drawable.ic_violin);
  }


  public static List<Artist> makeClassicArtists() {
    Artist beethoven = new Artist("Ludwig van Beethoven", false);
    Artist bach = new Artist("Johann Sebastian Bach", true);
    Artist brahms = new Artist("Johannes Brahms", false);
    Artist puccini = new Artist("Giacomo Puccini", false);

    return Arrays.asList(beethoven, bach, brahms, puccini);
  }

  public static Genre makeSalsaGenre() {
    return new Genre("Salsa", makeSalsaArtists(), R.drawable.ic_maracas);
  }



  public static List<Artist> makeSalsaArtists() {
    Artist hectorLavoe = new Artist("Hector Lavoe", true);
    Artist celiaCruz = new Artist("Celia Cruz", false);
    Artist willieColon = new Artist("Willie Colon", false);
    Artist marcAnthony = new Artist("Marc Anthony", false);

    return Arrays.asList(hectorLavoe, celiaCruz, willieColon, marcAnthony);
  }

  public static Genre makeBluegrassGenre() {
    return new Genre("Bluegrass", makeBluegrassArtists(), R.drawable.ic_banjo);
  }



  public static List<Artist> makeBluegrassArtists() {
    Artist billMonroe = new Artist("Bill Monroe", false);
    Artist earlScruggs = new Artist("Earl Scruggs", false);
    Artist osborneBrothers = new Artist("Osborne Brothers", true);
    Artist johnHartford = new Artist("John Hartford", false);

    return Arrays.asList(billMonroe, earlScruggs, osborneBrothers, johnHartford);
  }

}
