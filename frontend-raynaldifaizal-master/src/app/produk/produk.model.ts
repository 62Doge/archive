export class ProdukModel{
    id!: number;
    nama!: string;
    jenis!: string;
    harga!: number;
    berat!: string;
    produsen!: Produsen;

}

export class Produsen{
    id!: number;
    nama!: string;
}