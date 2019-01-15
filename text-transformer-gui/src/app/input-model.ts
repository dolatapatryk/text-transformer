export class InputModel {
    public text: string;
    public transforms: string[];

    public constructor(init?:Partial<InputModel>) {
        Object.assign(this, init);
    }
}