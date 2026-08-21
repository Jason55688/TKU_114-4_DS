public class MediaProcessingSystem {

    public static void main(String[] args) {

        MediaFile[] files = {

                new ImageFile("photo.jpg"),
                new AudioFile("music.mp3"),
                new VideoFile("movie.mp4")
        };

        System.out.println("=== Media Processing System ===\n");

        for (MediaFile file : files) {

            System.out.println("File: " + file.getFileName());

            file.showInfo();

            // 支援播放
            if (file instanceof Playable playable) {
                playable.play();
            }

            // 支援壓縮
            if (file instanceof Compressible compressible) {
                compressible.compress();
            }

            System.out.println();
        }
    }
}

// 抽象父類別
abstract class MediaFile {

    protected String fileName;

    public MediaFile(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public abstract void showInfo();
}

// 播放能力
interface Playable {

    void play();
}

// 壓縮能力
interface Compressible {

    void compress();
}

// 圖片檔
class ImageFile extends MediaFile
        implements Compressible {

    public ImageFile(String fileName) {
        super(fileName);
    }

    @Override
    public void showInfo() {

        System.out.println("Type: Image File");
    }

    @Override
    public void compress() {

        System.out.println(
                "Compressing image: "
                        + fileName);
    }
}

// 音訊檔
class AudioFile extends MediaFile
        implements Playable, Compressible {

    public AudioFile(String fileName) {
        super(fileName);
    }

    @Override
    public void showInfo() {

        System.out.println("Type: Audio File");
    }

    @Override
    public void play() {

        System.out.println(
                "Playing audio: "
                        + fileName);
    }

    @Override
    public void compress() {

        System.out.println(
                "Compressing audio: "
                        + fileName);
    }
}

// 影片檔
class VideoFile extends MediaFile
        implements Playable, Compressible {

    public VideoFile(String fileName) {
        super(fileName);
    }

    @Override
    public void showInfo() {

        System.out.println("Type: Video File");
    }

    @Override
    public void play() {

        System.out.println(
                "Playing video: "
                        + fileName);
    }

    @Override
    public void compress() {

        System.out.println(
                "Compressing video: "
                        + fileName);
    }
}