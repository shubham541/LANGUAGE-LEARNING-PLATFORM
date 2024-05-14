/**
 * Api Documentation
 * Api Documentation
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


export interface UserDto { 
    bio?: string;
    contactNumber?: string;
    dob?: string;
    firstName?: string;
    gender?: UserDto.GenderEnum;
    id?: string;
    lastName?: string;
    username?: string;
}
export namespace UserDto {
    export type GenderEnum = 'Male' | 'Female' | 'Prefer_Not_To_Say';
    export const GenderEnum = {
        Male: 'Male' as GenderEnum,
        Female: 'Female' as GenderEnum,
        PreferNotToSay: 'Prefer_Not_To_Say' as GenderEnum
    };
}
