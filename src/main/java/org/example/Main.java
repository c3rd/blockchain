package org.example;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Main {
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;
    public static void main(String[] args) {

        blockchain.add(new Block("0", "First block"));
        System.out.println("Trying to mine block 1");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block(blockchain.get(blockchain.size()-1).hash, "Second block"));
        System.out.println("Trying to mine block 2");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block(blockchain.get(blockchain.size()-1).hash, "Third block"));
        System.out.println("Trying to mine block 3");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is Valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);

    }

    public static Boolean isChainValid() {

        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < blockchain.size(); i++) {

            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current hashes are not equal");
                return false;
            }

            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous hash is not equals to currents");
                return false;
            }

        }

        return true;
    }
}