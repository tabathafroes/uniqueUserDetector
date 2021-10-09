package com.fatec.backjava.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import com.fatec.backjava.domain.DadosEntropicosUser;
import com.fatec.backjava.dto.UsuarioDTO;

public class MD5 {

    public String encrypt(DadosEntropicosUser dadosEntropicosUser, UsuarioDTO usuarioDTO) throws Exception {
    	String dados =  usuarioDTO.getTelefone() + 
    			dadosEntropicosUser.getIp() +
    			dadosEntropicosUser.getDeviceMemory() +
    			dadosEntropicosUser.getHardwareConcurrency() +
    			dadosEntropicosUser.getPlatform() +
    			dadosEntropicosUser.getVendorFlavors();
    	String veridigit = dadosEntropicosUser.getIp().substring(dadosEntropicosUser.getIp().length() - 1);
    	
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(dados.getBytes(),0,dados.length());
        int valorConvertido = converter(veridigit);
        return (new BigInteger(1,m.digest()).toString(valorConvertido));
            
    }
    
    public int converter(String verificador) {
    	int novoNumero = Integer.valueOf(verificador);
    	return novoNumero + 17;
    }
}
