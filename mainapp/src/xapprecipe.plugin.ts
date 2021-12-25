import { registerPlugin } from '@capacitor/core';


export interface XAppRecipePlugin {
  launch(): void;
}


const pluginXAppRecipe = registerPlugin<XAppRecipePlugin>('XAppRecipe');


export default pluginXAppRecipe;
