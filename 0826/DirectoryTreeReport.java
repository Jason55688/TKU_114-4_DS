import java.util.ArrayList;
import java.util.List;

public class DirectoryTreeReport {

    public static class FileNode {
        String name;
        boolean isDirectory;
        long size;
        List<FileNode> children = new ArrayList<>();

        public FileNode(String name, long size) {
            this.name = name;
            this.isDirectory = false;
            this.size = size;
        }

        public FileNode(String name) {
            this.name = name;
            this.isDirectory = true;
            this.size = 0;
        }

        public void addChild(FileNode child) {
            if (this.isDirectory) {
                this.children.add(child);
            }
        }
    }

    private static FileNode largestFile = null;

    public static long calculateDirectorySizes(FileNode node) {
        if (node == null) return 0;
        if (!node.isDirectory) {
            if (largestFile == null || node.size > largestFile.size) {
                largestFile = node;
            }
            return node.size;
        }
        long total = 0;
        for (FileNode child : node.children) {
            total += calculateDirectorySizes(child);
        }
        node.size = total;
        return total;
    }

    public static int countTotalNodes(FileNode node) {
        if (node == null) return 0;
        int count = 1;
        for (FileNode child : node.children) {
            count += countTotalNodes(child);
        }
        return count;
    }

    public static int countFiles(FileNode node) {
        if (node == null) return 0;
        if (!node.isDirectory) return 1;
        int count = 0;
        for (FileNode child : node.children) {
            count += countFiles(child);
        }
        return count;
    }

    public static int countDirectories(FileNode node) {
        if (node == null || !node.isDirectory) return 0;
        int count = 1;
        for (FileNode child : node.children) {
            count += countDirectories(child);
        }
        return count;
    }

    public static int height(FileNode node) {
        if (node == null) return 0;
        int maxChildHeight = 0;
        for (FileNode child : node.children) {
            maxChildHeight = Math.max(maxChildHeight, height(child));
        }
        return 1 + maxChildHeight;
    }

    public static void printTree(FileNode node, String indent) {
        if (node == null) return;
        System.out.println(indent + (node.isDirectory ? "[Dir] " : "[File] ") + node.name + " (" + node.size + " bytes)");
        for (FileNode child : node.children) {
            printTree(child, indent + "  ");
        }
    }

    public static void main(String[] args) {
        FileNode root = new FileNode("root");
        FileNode docs = new FileNode("docs");
        FileNode media = new FileNode("media");

        docs.addChild(new FileNode("resume.pdf", 1200));
        docs.addChild(new FileNode("notes.txt", 400));

        FileNode photos = new FileNode("photos");
        photos.addChild(new FileNode("pic1.jpg", 5000));
        photos.addChild(new FileNode("pic2.png", 8500));
        media.addChild(photos);
        media.addChild(new FileNode("song.mp3", 7000));

        root.addChild(docs);
        root.addChild(media);
        root.addChild(new FileNode("config.sys", 150));

        largestFile = null;
        calculateDirectorySizes(root);

        System.out.println("--- Directory Structure & Computed Sizes ---");
        printTree(root, "");

        System.out.println("\n--- Report ---");
        System.out.println("Total Nodes: " + countTotalNodes(root));
        System.out.println("File Count: " + countFiles(root));
        System.out.println("Directory Count: " + countDirectories(root));
        System.out.println("Height: " + height(root));
        System.out.println("Largest File: " + (largestFile != null ? largestFile.name + " (" + largestFile.size + " bytes)" : "None"));
    }
}