package com.bootcamp.hellobiqri.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TransaksiDto {

    @Data
    @NoArgsConstructor
    public static class Create{
        @Min(1)
        @NotNull
        Integer kuantitas;


        @Min(1)
        @NotNull
        Integer produk_id;

    }

    @Data
    @NoArgsConstructor
    public static class Update{

        @NotNull
        @Min(1)
        Integer id;

        @Min(1)
        @NotNull
        Integer kuantitas;


        @Min(1)
        @NotNull
        Integer produk_id;

    }

    @Data
    @NoArgsConstructor
    public static class Detail {


        Integer id;


        String produk;


        String produsen;


        Double harga;


        Integer kuantitas;


        Double totalHarga;

    }


}
