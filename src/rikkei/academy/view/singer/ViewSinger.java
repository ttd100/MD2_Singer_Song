package rikkei.academy.view.singer;

import rikkei.academy.config.Config;
import rikkei.academy.controller.SingerController;
import rikkei.academy.model.Singer;

import java.util.List;

public class ViewSinger {
    public SingerController singerController = new SingerController();
    public List<Singer> singerList = singerController.showListSinger();

    public ViewSinger() {
        System.out.println("**********Singer*******");
        System.out.println("1.Create list singer");
        System.out.println("2.Show list singer");
        System.out.println("3.Detail Singer");
        System.out.println("4.Update Singer");
        System.out.println("5.Delete Singer");
        System.out.println("6.Sort Singer");
        int chooseSinger = Config.scanner().nextInt();
        switch (chooseSinger) {
            case 1:
                formCreateSinger();
                break;
            case 2:
                formShowListSinger();
                break;
            case 3:
                formDetailSinger();
                break;
            case 4:
                formEditSinger();
                break;
            case 5:
                formDeleteSinger();
                break;
            case 6:
                formShowListSingerAfterSort();
                break;

        }
    }

    public void formCreateSinger() {
        System.out.println("__________create Singer_________");
        while (true) {
            int idSinger;
            if (singerList.size() == 0) {
                idSinger = 1;
            } else {
                idSinger = singerList.get(singerList.size() - 1).getId() + 1;
            }
            System.out.println("nhập vào tên Singer: ");
            String name = Config.scanner().nextLine();
            System.out.println("nhập vào tuổi: ");
            int age = Config.scanner().nextInt();
            Singer singer = new Singer(idSinger, name, age);
            singerController.createSinger(singer);
            System.out.println("create success" );
            singerController.showListSinger();
//            System.out.println("check list ---->" + singerController.showListSinger());
//            System.out.println("nhập phím bất kì để tiếp tục - nhập  vào quit để thoát");
//            String backMenu = Config.scanner().nextLine();
//            if (backMenu.equalsIgnoreCase("quit")) {
//                new ViewSinger();
//                break;
//            }

        }
    }

    public void formShowListSinger() {
        System.out.println("_________show list Singer________");
        for (int i = 0; i < singerList.size(); i++) {
            int j = i + 1;
            System.out.println("|======" + j + "======" + singerList.get(i).getName() + "=====" + singerList.get(i).getAge() + "=====|");
        }
        System.out.println("nhập vào quit để thoát");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewSinger();
        }
    }

    public void formDetailSinger() {
        System.out.println("nhập vào id của Singer:");
        int idSinger = Config.scanner().nextInt();
        if (singerController.detailSinger(idSinger) == null) {
            System.out.println("không tồn tại");
        } else {
            Singer singer = singerController.detailSinger(idSinger);
            System.out.println(singer);
        }
        System.out.println("nhập vào quit để thoát");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewSinger();
        }
    }

    public void formEditSinger() {
        System.out.println("nhập vào id để sửa: ");
        int idSinger = Config.scanner().nextInt();
        if (singerController.detailSinger(idSinger) == null) {
            System.out.println("không tồn tại");
        } else {
            Singer singer = singerController.detailSinger(idSinger);
            System.out.println("old name " + singer.getName());
            System.out.println("old age " + singer.getAge());
            System.out.println("nhập vào tên để sửa");
            String newName = Config.scanner().nextLine();
            if (newName.trim().equals("")) {
                newName = singer.getName();
            }
            System.out.println("nhập vào tuổi để sửa");
            String newAge = Config.scanner().nextLine();
            if (newAge.trim().equals("")){
                newAge = String.valueOf(singer.getAge());
            }
            Singer newSinger = new Singer(newName,newAge);
            singerController.updateSinger(idSinger,newSinger);
            System.out.println("Edit success");
            singerController.showListSinger();
//            System.out.println("check list ---->" + singerController.showListSinger());
        } System.out.println("nhập vào quit để thoát");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewSinger();
        }
    }
    public void formDeleteSinger(){
        System.out.println("nhập vào id để xóa");
        int idSinger = Config.scanner().nextInt();
        if (singerController.detailSinger(idSinger)==null){
            System.out.println("không tồn tại");
        }else {
            System.out.println("nhập 1 để xóa - nhập 2 không xóa");
            int chooseDelete = Config.scanner().nextInt();
            switch (chooseDelete){
                case 1:
                    singerController.deleteSinger(idSinger);
                    formShowListSinger();
                    singerController.showListSinger();
                    break;
                case 2:
                    new ViewSinger();
                    break;
            }
        }
    }
    public void formShowListSingerAfterSort(){
        System.out.println("|======STT======NAME=====AGE======|");
        List<Singer> listSort = singerController.sortByNameAndAge();
        for (int i = 0; i < listSort.size(); i++) {
            int j = i+1;
            System.out.println("|======"+j+"======"+listSort.get(i).getName()+"===="+listSort.get(i).getAge()+"====|");
        }
        System.out.println("nhập vào quit để thoát");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewSinger();
        }

    }
}
