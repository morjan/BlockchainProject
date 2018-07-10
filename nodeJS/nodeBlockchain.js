var express = require("express");
var bodyParser = require('body-parser');
var CryptoJS = require("crypto-js");
var http_port = process.env.HTTP_PORT || 3001;

class Block {
    constructor(index, previousHash, timestamp, data, hash) {
        this.index = index;
        this.previousHash = previousHash.toString();
        this.timestamp = timestamp;
        this.data = data;
        this.hash = hash.toString();
    }
}

var initHttpServer = () => {
    var app = express();
    app.use(bodyParser.json());
    

    app.get('/blocks', (req, res) => res.send(JSON.stringify(blockchain)));

    app.post('/mineBlock', (req, res) => {
        var newBlock = generateNextBlock(req.body.data);
        addBlock(newBlock);
        console.log('block added: ' + JSON.stringify(req.body.data));
        res.send();
    });

    app.listen(http_port, () => console.log('Listening http on port: ' + http_port));
};
initHttpServer();


var getGenesisBlock = new Block(0, "0", 1465154705, "my genesis block!!", "816534932c2b7154836da6afc367695e6337db8a921823784c14378abed4f7d7");
var blockchain = [getGenesisBlock];

var calculateHash = (index, previousHash, timestamp, data) => {
    return CryptoJS.SHA256(index + previousHash + timestamp + data).toString();
};
var calculateHashForBlock = (block) => {
    return calculateHash(block.index, block.previousHash, block.timestamp, block.data);
};

var generateNextBlock = (blockData) => {
    var previousBlock = getLatestBlock();
    var nextIndex = previousBlock.index + 1;
    var nextTimestamp = new Date().getTime() / 1000;
    var nextHash = calculateHash(nextIndex, previousBlock.hash, nextTimestamp, blockData);
	
    return new Block(nextIndex, previousBlock.hash, nextTimestamp, blockData, nextHash);
};

var getLatestBlock = () => blockchain[blockchain.length - 1];

var addBlock = (newBlock) => {
    if (isValidNewBlock(newBlock, getLatestBlock())) {
        blockchain.push(newBlock);
    }
};

var isValidNewBlock = (newBlock, previousBlock) => {
    if (previousBlock.index + 1 !== newBlock.index) {
        console.log('invalid index');
		
        return false;
    } else if (previousBlock.hash !== newBlock.previousHash) {
        console.log('invalid previoushash');
		
        return false;
    } else if (calculateHashForBlock(newBlock) !== newBlock.hash) {
        console.log('invalid hash: ' + calculateHashForBlock(newBlock) + ' ' + newBlock.hash);
		
        return false;
    }	
    return true;
};

