package rikkei.academy.service.singer;

import rikkei.academy.config.Config;
import rikkei.academy.model.Singer;
import rikkei.academy.service.IGenericService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SingerServiceIMPL implements ISingerService {
    public static final String PATH_SINGER = "D:\\MD2_TEST_MVC\\src\\rikkei\\academy\\database\\singer.txt";
    public static List<Singer> singerList = new Config<Singer>().readFile(PATH_SINGER);
//    static {
//        singerList.add(new Singer(1,"Dat",19));
//        singerList.add(new Singer(2,"chinh",18));
//        singerList.add(new Singer(3,"chi",16));
//        singerList.add(new Singer(4,"Dat",16));
//    }

    @Override
    public List findAll() {
        new Config<Singer>().writeFile(PATH_SINGER,singerList);
        return singerList;
    }

    @Override
    public void save(Singer singer) {
        singerList.add(singer);
    }

    @Override
    public Singer findByID(int id) {
        for (int i = 0; i < singerList.size(); i++) {
            if (id == singerList.get(i).getId()){
                return singerList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteByID(int id) {
        for (int i = 0; i < singerList.size(); i++) {
            if (id == singerList.get(i).getId()){
                singerList.remove(i);
            }
        }
    }

    @Override
    public List<Singer> sortByNameAndByAge() {
        List<Singer> listSort = new ArrayList<>();
        for (int i = 0; i < singerList.size(); i++) {
            listSort.add(singerList.get(i));
        }
        Collections.sort(listSort);
        return listSort;
    }
}
