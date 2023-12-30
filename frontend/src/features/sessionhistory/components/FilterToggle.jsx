import {useState} from "react";
import {Dialog} from "@headlessui/react";
import FilterModal from "../filters/FilterModal.jsx";
import OutlineButton from "../../../components/buttons/OutlineButton.jsx"
import IconButtonWithText from "../../../components/buttons/IconButtonWithText.jsx";
import CloseIconButton from "../../../components/buttons/CloseIconButton.jsx";

export default function FilterToggle({setHistoryDTO}) {

    const [isOpen, setIsOpen] = useState(false)

    const handleToggleOpen = () => {
        setIsOpen(true);
    }

    const handleToggleClose = () => {
        setIsOpen(false);
    }

    return (
        <>
            <IconButtonWithText
                iconSvg={ // taken from https://heroicons.com/outline
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M10.5 6h9.75M10.5 6a1.5 1.5 0 1 1-3 0m3 0a1.5 1.5 0 1 0-3 0M3.75 6H7.5m3 12h9.75m-9.75 0a1.5 1.5 0 0 1-3 0m3 0a1.5 1.5 0 0 0-3 0m-3.75 0H7.5m9-6h3.75m-3.75 0a1.5 1.5 0 0 1-3 0m3 0a1.5 1.5 0 0 0-3 0m-9.75 0h9.75" />
                </svg>
                }
                handleOnClick={handleToggleOpen}
                buttonText={"Filter"} />
            <Dialog
                open={isOpen}
                onClose={() => setIsOpen(false)}
                className="relative z-50"
            >

                <div className="fixed inset-0 bg-black/80" aria-hidden="true" />

                <div className="fixed inset-0 w-screen overflow-y-auto">
                    <div className="flex min-h-full items-center justify-center p-4">
                        <Dialog.Panel className="mx-auto w-[350px] rounded bg-white">
                            <div className="flex justify-between items-center mb-2">
                                <Dialog.Title
                                    className="text-2xl font-bold mb-2 text-indigo-600 pl-4 pt-4">Filters
                                </Dialog.Title>
                                <div className="pr-4 pt-2">
                                    <CloseIconButton handleOnClick={handleToggleClose} />
                                </div>

                            </div>
                            <div>
                                <FilterModal setHistoryDTO={setHistoryDTO} handleToggleClose={handleToggleClose}/>
                            </div>
                        </Dialog.Panel>
                    </div>
                </div>
            </Dialog>

        </>
    )





}