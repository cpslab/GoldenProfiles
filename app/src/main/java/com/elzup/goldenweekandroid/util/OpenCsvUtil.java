package com.elzup.goldenweekandroid.util;

import com.elzup.goldenweekandroid.models.GoldenUser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class OpenCsvUtil {


    /**
     * Csv を受け取り opencsv を使い Bean に parse する
     * @param csvText
     * @return
     */
    public static List<GoldenUser> toGoldenUsers(String csvText) {
        List<GoldenUser> users = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new StringReader(csvText));
            reader.readNext();

            for (String[] params: reader.readAll()) {
                GoldenUser user = new GoldenUser();
                // ニックネーム,学籍番号,今期アニメ,好きなアニメ,好きな言語,画像
                user.setName(params[0]);
                user.setStudentID(params[1]);
                user.setCurrentAnime(params[2]);
                user.setFavoriteAnime(params[3]);
                user.setLanguage(params[4]);
                user.setImgURL(params[5]);
                users.add(user);
            }

            // NOTE: 謎の opencv Error
            // ColumnPositionMappingStrategy<GoldenUser> strat = new ColumnPositionMappingStrategy<>();
            // strat.setType(GoldenUser.class);
            // strat.setColumnMapping(HEADER);
            // CsvToBean<GoldenUser> csv = new CsvToBean<>();
            // return csv.parse(strat, reader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
