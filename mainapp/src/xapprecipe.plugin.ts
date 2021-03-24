import { registerPlugin } from "@capacitor/core";


export interface XAppRecipePlugin {
  launch(): void;
}


const XAppRecipe = registerPlugin<XAppRecipePlugin>("XAppRecipe");


export default XAppRecipe;
