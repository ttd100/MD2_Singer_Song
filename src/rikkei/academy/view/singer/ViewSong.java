package rikkei.academy.view.singer;

import rikkei.academy.config.Config;
import rikkei.academy.controller.SingerController;
import rikkei.academy.controller.SongController;
import rikkei.academy.model.Singer;
import rikkei.academy.model.Song;

import java.util.ArrayList;
import java.util.List;

public class ViewSong {
    private SongController songController = new SongController();
    private List<Song> songList = new SongController().showListSong();
    private SingerController singerController = new SingerController();
    List<Singer> singerList = singerController.showListSinger();

    public ViewSong() {
        System.out.println("**********Song*******");
        System.out.println("1.Create list Song");
        System.out.println("2.Show list Song");
        System.out.println("3.Detail Song");
        System.out.println("4.Top like Song");
        System.out.println("5.Top listen Song");

        int chooseSong = Config.scanner().nextInt();
        switch (chooseSong) {
            case 1:
                formCreateSong();
                break;
            case 2:
                formShowListSong();
                break;
            case 3:
                formDetailSong();
                break;
            case 4:
                formTopLikeSong();
                break;
            case 5:
                formTopListen();
                break;
        }
    }

    private void formCreateSong() {
        int idSong;
        if (songList.size() == 0) {
            idSong = 1;
        } else {
            idSong = songList.get(songList.size() - 1).getId() + 1;
        }
        System.out.println("Enter the name Song");
        String name = Config.scanner().nextLine();
        List<Singer> listSelect = new ArrayList<>();
        listSelect = selectSinger(listSelect);
        Song song = new Song(idSong, name, 0, 0, listSelect);
        songController.createSong(song);
        songController.showListSong();
        System.out.println("CREATE SONG SUCCESS");
        showListSong();
    }

    private void formShowListSong() {
        showListSong();
        System.out.println("Enter any key to continue  - Enter quit to exit select Singer");
        String exitSelect = Config.scanner().nextLine();
        if (exitSelect.equalsIgnoreCase("quit")) {
            new ViewSong();
        }
    }

    private void showListSong() {
        System.out.println("====ID=====NAME======LISTEN======LIKE=====");
        for (int i = 0; i < songList.size(); i++) {
            System.out.println("======" + songList.get(i).getId() + "======" + songList.get(i).getName() + "=====" + songList.get(i).getListen()
                    + "====" + songList.get(i).getLike() + "====" + songList.get(i).getSingerList());
        }
    }

    private List<Singer> selectSinger(List<Singer> listSelect) {
        System.out.println("=====Id=====Name======");
        for (int i = 0; i < singerList.size(); i++) {
            System.out.println("====" + singerList.get(i).getId() + "====" + singerList.get(i).getName());
        }
        System.out.println("Enter the id of singer to select");
        int idSinger = Config.scanner().nextInt();
        Singer singer = singerController.detailSinger(idSinger);
        listSelect.add(singer);
        System.out.println("Enter any key to continue - Enter QUit to exit select Singer");
        String exitSelect = Config.scanner().nextLine();
        if (exitSelect.equalsIgnoreCase("quit")) {
            return listSelect;
        } else {
            selectSinger(listSelect);
        }
        return listSelect;
    }
    private void formDetailSong(){
        showListSong();
        System.out.println("Enter the id Song");
        int idSong = Config.scanner().nextInt();
        if (songController.detailSong(idSong)==null){
            System.out.println("the song doesn't exits");
        }else {
            Song song = songController.detailSong(idSong);
            song.setListen(song.getListen()+1);
            songController.showListSong();
            System.out.println("====ID=====NAME======LISTEN======LIKE=====LIST SINGER");
            System.out.println(song.getId()+"===="+song.getName()+"===="+song.getListen()+"===="+song.getLike()+"=====");
            for (int i = 0; i < song.getSingerList().size() ; i++) {
                System.out.println(song.getSingerList().get(i).getName()+" , ");
            }
            System.out.println("please LIKE ME? Enter like");
            String like = Config.scanner().nextLine();
            if (like.equalsIgnoreCase("like")){
                song.setLike(song.getLike()+1);
                songController.showListSong();
            }
            System.out.println("Enter quit to come back Song ");
            String exitSelect = Config.scanner().nextLine();
            if (exitSelect.equalsIgnoreCase("quit")) {
                new ViewSong();
            }
        }
    }
    private void formTopLikeSong(){
        for (int i = 0; i < songController.topLike().size(); i++) {
            System.out.println(songController.topLike().get(i));
        }
    }
    private void formTopListen(){
        for (int i = 0; i < songController.topListen().size(); i++) {
            System.out.println(songController.topListen().get(i));
        }
    }
}

