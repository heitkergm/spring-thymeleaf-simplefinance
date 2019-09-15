package com.dappermoose.stsimplefinance.data;

// TODO: Auto-generated Javadoc
/**
 * The Enum YesNoEnum.
 */
public enum YesNoEnum
{
    /** The yes. */
    YES,
    /** The no. */
    NO;

    /**
     * convert Enum to boolean.
     *
     * @param value the enum value
     * @return the corresponding boolean
     */
    @SuppressWarnings ("PMD.UnnecessaryFullyQualifiedName")
    public static boolean toBoolean (final YesNoEnum value)
    {
        boolean retVal = false;

        if (value.equals (YesNoEnum.YES))
        {
            retVal = true;
        }

        return retVal;
    }
}
