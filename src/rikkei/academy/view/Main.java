package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.view.singer.ViewSinger;
import rikkei.academy.view.singer.ViewSong;

public class Main {
    public Main(){
        System.out.println("=======welcome to zing mp3 Star========");
        System.out.println("1.Singer");
        System.out.println("2.Song");
        int chooseMenu = Config.scanner().nextInt();
        switch (chooseMenu){
            case 1:
                new ViewSinger();
                break;
            case 2:
                new ViewSong();
                break;
        }
    }
    public static void main(String[] args) {
        new Main();
    }
}